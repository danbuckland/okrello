package com.blocksolid.okrello;

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
import com.blocksolid.okrello.model.TrelloCheckItem;
import com.blocksolid.okrello.model.TrelloChecklist;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class KeyResultsActivity extends AppCompatActivity {

    public static TrelloApi trelloApi;
    public static ArrayList<TrelloCheckItem> keyResults;
    public static TrelloCard trelloCard;
    public static ProgressBar keyresProgressBar;
    public static TextView actionBarTitle;
    public String cardId;
    public String objective;
    public String checklistId;
    KeyResultAdapter keyResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_results);

        trelloApi = ServiceGenerator.createService(TrelloApi.class);

        // Create custom Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        // Set Toolbar title to the selected Objective name
        objective = this.getIntent().getExtras().getString("objective");
        checklistId = this.getIntent().getExtras().getString("checklistId");

        actionBarTitle = (TextView) findViewById(R.id.toolbar_title);
        actionBarTitle.setText(objective);

        keyresProgressBar = (ProgressBar) findViewById(R.id.keyres_progress);
        keyresProgressBar.setVisibility(View.INVISIBLE);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.keyres_recycler_key_results);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        keyResultAdapter = new KeyResultAdapter();
        mRecyclerView.setAdapter(keyResultAdapter);

        cardId = this.getIntent().getExtras().getString("cardId");

        trelloCard = new TrelloCard();

        getKeyResults();
    }

    private void getKeyResults() {
        // Show progress indicator while working
        keyresProgressBar.setVisibility(View.VISIBLE);

        // Define the request
        String fields = "name";
        final Call<ArrayList<TrelloChecklist>> call = trelloApi.getChecklists(cardId, TrelloApi.KEY, fields);

        // Make the request
        call.enqueue(new Callback<ArrayList<TrelloChecklist>>() {

            @Override
            public void onResponse(Call<ArrayList<TrelloChecklist>> cardCall, Response<ArrayList<TrelloChecklist>> response) {
                trelloCard.setChecklists(response.body());
                // Populate an ArrayList of checkItems called keyResults
                keyResults = trelloCard.getKeyResultsCheckitems();
                Collections.sort(keyResults);
                // Update data in custom view adapter
                keyResultAdapter.updateData(keyResults);
                // Hide progress indicator when done
                keyresProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<TrelloChecklist>> cardCall, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Retrofit", t.getMessage());
                keyresProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
