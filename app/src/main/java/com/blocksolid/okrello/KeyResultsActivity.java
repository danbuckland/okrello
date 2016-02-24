package com.blocksolid.okrello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blocksolid.okrello.api.ServiceGenerator;
import com.blocksolid.okrello.api.TrelloApi;
import com.blocksolid.okrello.model.TrelloCard;
import com.blocksolid.okrello.model.TrelloCheckItem;
import com.blocksolid.okrello.model.TrelloChecklist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class KeyResultsActivity extends AppCompatActivity {

    public static TrelloApi trelloApi;
    public static ArrayList<TrelloChecklist> trelloChecklists;
    public static ArrayList<TrelloCheckItem> keyResults;
    public static TrelloCard trelloCard;
    public static ListView listView;
    public static ProgressBar keyresProgressBar;
    public String cardId;
    public String objective;
    public String checklistId;
    KeyResultAdapter keyResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_results);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        trelloApi = ServiceGenerator.createService(TrelloApi.class);

        // Set Activity title to the selected list name
        objective = this.getIntent().getExtras().getString("objective");
        checklistId = this.getIntent().getExtras().getString("checklistId");
        setTitle(objective);

        keyresProgressBar = (ProgressBar) findViewById(R.id.keyres_progress);
        keyresProgressBar.setVisibility(View.INVISIBLE);

        listView = (ListView) findViewById(R.id.keyres_list_key_results);

        cardId = this.getIntent().getExtras().getString("cardId");

        keyResultAdapter = new KeyResultAdapter(this, getLayoutInflater());
        listView.setAdapter(keyResultAdapter);

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
                trelloChecklists = response.body();
                // Update data in custom view adapter
                // TODO decide which checklist is the correct one to use
                // TODO extract this method out to somewhere else - not sure where

                // Populate an ArrayList of checkItems called keyResults
                keyResults = getKeyResultsCheckitems(trelloChecklists);
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

    public ArrayList<TrelloCheckItem> getKeyResultsCheckitems(ArrayList<TrelloChecklist> trelloChecklists) {
        ArrayList<TrelloCheckItem> keyResults = null;
        if (trelloChecklists.size() == 1) {
            // If there's only one checklist, use that checklist as Key Results
            keyResults = trelloChecklists.get(0).getTrelloCheckItems();
        } else if (trelloChecklists.size() > 1) {
            // When card has more than one checklist belonging to a card
            // only checkItems belonging to the checklist called "Key Results" are displayed.
            for (TrelloChecklist checklist : trelloChecklists) {
                if (checklist.getName().equals("Key Results")) {
                    keyResults = checklist.getTrelloCheckItems();
                }
            }
        }
        // When there is no checklist called "Key Results" belonging to a card with
        // multiple checklists, then no Key Results are displayed.
        return keyResults;
    }
}
