package com.blocksolid.okrello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blocksolid.okrello.api.TrelloApi;
import com.blocksolid.okrello.model.TrelloCheckItem;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class KeyResultsActivity extends AppCompatActivity {

    public static ArrayList<TrelloCheckItem> trelloCheckItems;
    public static ListView listView;
    public static ProgressBar keyresProgressBar;
    public String cardId;
    public String cardName;
    KeyResultAdapter keyResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_results);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set Activity title to the selected list name
        cardName = this.getIntent().getExtras().getString("cardName");
        setTitle(cardName);

        keyresProgressBar = (ProgressBar) findViewById(R.id.keyres_progress);
        keyresProgressBar.setVisibility(View.INVISIBLE);

        listView = (ListView) findViewById(R.id.keyres_list_key_results);

        cardId = this.getIntent().getExtras().getString("cardId");

        keyResultAdapter = new KeyResultAdapter(this, getLayoutInflater());
        listView.setAdapter(keyResultAdapter);

        getKeyResults();
    }

    private String getKeyResultsChecklistId() {
        // TODO add logic and unit tests to deduce which checklist contains key results
        return "5665991c7c27445d260de813";
    }

    private void getKeyResults() {
        // Show progress indicator while working
        keyresProgressBar.setVisibility(View.VISIBLE);

        String checklistId = getKeyResultsChecklistId();

        // Retrofit stuff starts here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TrelloApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrelloApi trelloApi = retrofit.create(TrelloApi.class);

        // Define the request
        String fields = "name";
        final Call<ArrayList<TrelloCheckItem>> call = trelloApi.getCheckItems(checklistId, TrelloApi.KEY, fields);

        // Make the request
        call.enqueue(new Callback<ArrayList<TrelloCheckItem>>() {

            @Override
            public void onResponse(Response<ArrayList<TrelloCheckItem>> response, Retrofit retrofit) {
                trelloCheckItems = response.body();
                // Update data in custom view adapter
                keyResultAdapter.updateData(trelloCheckItems);
                // Hide progress indicator when done
                keyresProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Retrofit", t.getMessage());
                keyresProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
