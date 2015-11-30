package com.blocksolid.okrello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blocksolid.okrello.api.TrelloApi;
import com.blocksolid.okrello.model.TrelloList;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.trello.com/1/";
    public static final String BOARD_ID = "5RMq1Nyb";
    public static final String KEY = "cf2308ac2c68ab9a54037478108439e4";

    public static ProgressBar progressBar;
    public static Button getListsBtn;
    public static List<TrelloList> trelloLists;
    public static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        progressBar.setVisibility(View.INVISIBLE);

        getListsBtn = (Button) findViewById(R.id.main_btn_get_lists);
        listView = (ListView) findViewById(R.id.main_list_lists);

        getListsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLists();
            }
        });
    }

    private void getLists() {
        // Show progress indicator while working
        progressBar.setVisibility(View.VISIBLE);

        // TODO make retrofit call to capture Lists to an array
        // Retrofit stuff starts here
        // Retrofit stuff here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrelloApi trelloApi = retrofit.create(TrelloApi.class);



        // Make the call
        final Call<List<TrelloList>> call = trelloApi.getLists(BOARD_ID, KEY);
        call.enqueue(new Callback<List<TrelloList>>() {


            @Override
            public void onResponse(Response<List<TrelloList>> response, Retrofit retrofit) {
                trelloLists = response.body();

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
}
