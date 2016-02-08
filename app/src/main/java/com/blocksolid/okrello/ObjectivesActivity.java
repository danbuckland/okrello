package com.blocksolid.okrello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blocksolid.okrello.api.ServiceGenerator;
import com.blocksolid.okrello.api.TrelloApi;
import com.blocksolid.okrello.model.TrelloCard;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class ObjectivesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static TrelloApi trelloApi;
    public static ArrayList<TrelloCard> trelloCards;
    public static ListView listView;
    public static ProgressBar objsProgressBar;
    public String listId;
    public String listName;
    ObjectiveAdapter objectiveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectives);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        trelloApi = ServiceGenerator.createService(TrelloApi.class);

        // Set Activity title to the selected list name
        listName = this.getIntent().getExtras().getString("listName");
        setTitle(listName);

        objsProgressBar = (ProgressBar) findViewById(R.id.objs_progress);
        objsProgressBar.setVisibility(View.INVISIBLE);

        listView = (ListView) findViewById(R.id.objs_list_objectives);

        listId = this.getIntent().getExtras().getString("listId");

        objectiveAdapter = new ObjectiveAdapter(this, getLayoutInflater());
        listView.setAdapter(objectiveAdapter);
        listView.setOnItemClickListener(this);

        getCards();
    }

    private void getCards() {
        // Show progress indicator while working
        objsProgressBar.setVisibility(View.VISIBLE);

        // Define the request
        String fields = "name";
        final Call<ArrayList<TrelloCard>> call = trelloApi.getCards(listId, TrelloApi.KEY, fields);

        // Make the request
        call.enqueue(new Callback<ArrayList<TrelloCard>>() {

            @Override
            public void onResponse(Call<ArrayList<TrelloCard>> arrayListCall, Response<ArrayList<TrelloCard>> response) {
                trelloCards = response.body();
                // Update data in custom view adapter
                objectiveAdapter.updateData(trelloCards);
                // Hide progress indicator when done
                objsProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<TrelloCard>> arrayListCall, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Retrofit", t.getMessage());
                objsProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Grab the ID of the selected Trello List (Quarter)
        TrelloCard trelloCard = trelloCards.get(position);
        String checklistId = trelloCard.getKeyResultsChecklistId();
        String cardId = trelloCard.getId();
        String objective = trelloCard.getObjective();

        // Intent to take the user to a new KeyResultsActivity
        Intent keyResultsIntent = new Intent(this, KeyResultsActivity.class);

        // Pass across the list ID in the intent
        keyResultsIntent.putExtra("cardId", cardId);
        keyResultsIntent.putExtra("checklistId", checklistId);
        keyResultsIntent.putExtra("objective", objective);

        // start the next Activity using the above intent
        startActivity(keyResultsIntent);
    }
}
