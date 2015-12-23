package com.blocksolid.okrello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blocksolid.okrello.api.TrelloApi;
import com.blocksolid.okrello.model.TrelloList;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    public static final String BOARD_ID = "5RMq1Nyb";

    public static ProgressBar progressBar;
    public static Button refreshListsBtn;
    public static ArrayList<TrelloList> trelloLists;
    public static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        progressBar.setVisibility(View.INVISIBLE);

        refreshListsBtn = (Button) findViewById(R.id.main_btn_refresh_lists);
        listView = (ListView) findViewById(R.id.main_list_lists);

        refreshListsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLists();
            }
        });

        listView.setOnItemClickListener(this);
        getLists();
    }

    private void getLists() {
        // Show progress indicator while working
        progressBar.setVisibility(View.VISIBLE);

        // Retrofit stuff starts here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TrelloApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrelloApi trelloApi = retrofit.create(TrelloApi.class);

        // Define the request
        String fields = "name";
        final Call<ArrayList<TrelloList>> call = trelloApi.getLists(BOARD_ID, TrelloApi.KEY, fields);

        // Make the request
        call.enqueue(new Callback<ArrayList<TrelloList>>() {

            @Override
            public void onResponse(Response<ArrayList<TrelloList>> response, Retrofit retrofit) {
                trelloLists = response.body();

                // Get list names from response and add each to a new array
                ArrayList<String> trelloListsArray = new ArrayList<>();
                for (TrelloList listItem : trelloLists) {
                    trelloListsArray.add(listItem.getName());
                }

                // Populate list view with strings from array
                listView.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, trelloListsArray));

                // Hide progress indicator when done
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Retrofit", t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Grab the ID of the selected Trello List (Quarter)
        TrelloList trelloList = trelloLists.get(position);
        String listId = trelloList.getId();
        String listName = trelloList.getName();

        // Intent to take the user to a new ObjectivesActivity
        Intent objectivesIntent = new Intent(this, ObjectivesActivity.class);

        // Pass across the list ID in the intent
        objectivesIntent.putExtra("listId", listId);
        objectivesIntent.putExtra("listName", listName);

        // start the next Activity using the above intent
        startActivity(objectivesIntent);
    }
}
