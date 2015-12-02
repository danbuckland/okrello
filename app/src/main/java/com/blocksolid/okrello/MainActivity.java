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
import com.blocksolid.okrello.model.TrelloCard;
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

    public static final String TEST_LIST_ID = "565c9e7e21d1eaebe45903d7";

    public static ProgressBar progressBar;
    public static Button refreshListsBtn;
    public static Button getCardsBtn;
    public static List<TrelloList> trelloLists;
    public static List<TrelloCard> trelloCards;
    public static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        progressBar.setVisibility(View.INVISIBLE);

        refreshListsBtn = (Button) findViewById(R.id.main_btn_refresh_lists);
        getCardsBtn = (Button) findViewById(R.id.main_btn_get_cards);
        listView = (ListView) findViewById(R.id.main_list_lists);

        refreshListsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLists();
            }
        });
        getCardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCards();
            }
        });

        getLists();
    }

    private void getLists() {
        // Show progress indicator while working
        progressBar.setVisibility(View.VISIBLE);

        // Retrofit stuff starts here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrelloApi trelloApi = retrofit.create(TrelloApi.class);

        // Define the request
        final Call<List<TrelloList>> call = trelloApi.getLists(BOARD_ID, KEY);

        // Make the request
        call.enqueue(new Callback<List<TrelloList>>() {

            @Override
            public void onResponse(Response<List<TrelloList>> response, Retrofit retrofit) {
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

    private void getCards() {
        // Show progress indicator while working
        progressBar.setVisibility(View.VISIBLE);

        // Retrofit stuff starts here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrelloApi trelloApi = retrofit.create(TrelloApi.class);

        // Define the request
        final Call<List<TrelloCard>> call = trelloApi.getCards(TEST_LIST_ID, KEY);

        // Make the request
        call.enqueue(new Callback<List<TrelloCard>>() {

            @Override
            public void onResponse(Response<List<TrelloCard>> response, Retrofit retrofit) {
                trelloCards = response.body();

                // Get list names from response and add each to a new array
                ArrayList<String> trelloCardsArray = new ArrayList<>();
                for (TrelloCard listItem : trelloCards) {
                    trelloCardsArray.add(listItem.getName());
                }

                // Populate list view with strings from array
                listView.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, trelloCardsArray));

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
