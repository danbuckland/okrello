package com.blocksolid.okrello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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
public class ObjectivesActivity extends AppCompatActivity {

    public static TrelloApi trelloApi;
    public static ArrayList<TrelloCard> trelloCards;
    public static TextView actionBarTitle;
    public static ProgressBar objsProgressBar;
    public String listId;
    public String listName;
    ObjectiveAdapter objectiveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectives);

        trelloApi = ServiceGenerator.createService(TrelloApi.class);

        // Create custom Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Set Toolbar title to the selected list name
        listName = this.getIntent().getExtras().getString("listName");
        actionBarTitle = (TextView) findViewById(R.id.toolbar_title);
        actionBarTitle.setText(listName);

        // TODO fix progress indicator when getting objectives
        objsProgressBar = (ProgressBar) findViewById(R.id.objs_progress);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.objs_recycler_objectives);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        objectiveAdapter = new ObjectiveAdapter(this);
        mRecyclerView.setAdapter(objectiveAdapter);

        listId = this.getIntent().getExtras().getString("listId");

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

    public void viewKeyResultsActivity(TrelloCard currentItem, View v) {

        //Grab the ID of the selected Trello List (Quarter)
        String cardId = currentItem.getId();
        String objective = currentItem.getObjective();

        // Intent to take the user to a new KeyResultsActivity
        Intent keyResultsIntent = new Intent(v.getContext(), KeyResultsActivity.class);

        // Pass across the list ID in the intent
        keyResultsIntent.putExtra("cardId", cardId);
        keyResultsIntent.putExtra("objective", objective);

        // start the next Activity using the above intent
        v.getContext().startActivity(keyResultsIntent);
    }
}
