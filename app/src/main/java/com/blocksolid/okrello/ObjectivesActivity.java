package com.blocksolid.okrello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blocksolid.okrello.api.TrelloApi;
import com.blocksolid.okrello.model.TrelloCard;
import com.blocksolid.okrello.display.Scores;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class ObjectivesActivity extends AppCompatActivity {

    public static List<TrelloCard> trelloCards;
    public static ListView listView;
    public static Button refreshCardsBtn;
    public static ProgressBar objsProgressBar;
    public String listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectives);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        objsProgressBar = (ProgressBar) findViewById(R.id.objs_progress);
        objsProgressBar.setVisibility(View.INVISIBLE);

        refreshCardsBtn = (Button) findViewById(R.id.objs_btn_refresh_cards);
        listView = (ListView) findViewById(R.id.objs_list_cards);

        listId = this.getIntent().getExtras().getString("listId");

        refreshCardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCards();
            }
        });

        getCards();
    }

    private void getCards() {
        // Show progress indicator while working
        objsProgressBar.setVisibility(View.VISIBLE);

        // Retrofit stuff starts here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TrelloApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrelloApi trelloApi = retrofit.create(TrelloApi.class);

        // Define the request
        final Call<List<TrelloCard>> call = trelloApi.getCards(listId, TrelloApi.KEY);

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
                listView.setAdapter(new ArrayAdapter<>(ObjectivesActivity.this, android.R.layout.simple_list_item_1, trelloCardsArray));

                // Hide progress indicator when done
                objsProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Retrofit", t.getMessage());
                objsProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
