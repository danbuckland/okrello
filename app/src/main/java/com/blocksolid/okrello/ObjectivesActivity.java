package com.blocksolid.okrello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blocksolid.okrello.api.TrelloApi;
import com.blocksolid.okrello.model.TrelloCard;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Dan Buckland on 06/12/2015.
 */
public class ObjectivesActivity extends AppCompatActivity {

    public static ArrayList<TrelloCard> trelloCards;
    public static ListView listView;
    public static Button refreshCardsBtn;
    public static ProgressBar objsProgressBar;
    public String listId;
    public String listName;
    ObjectiveAdapter objectiveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectives);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set Activity title to the selected list name
        listName = this.getIntent().getExtras().getString("listName");
        setTitle(listName);

        objsProgressBar = (ProgressBar) findViewById(R.id.objs_progress);
        objsProgressBar.setVisibility(View.INVISIBLE);

        listView = (ListView) findViewById(R.id.objs_list_objectives);

        listId = this.getIntent().getExtras().getString("listId");

        objectiveAdapter = new ObjectiveAdapter(this, getLayoutInflater());
        listView.setAdapter(objectiveAdapter);

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
        String fields = "name";
        final Call<ArrayList<TrelloCard>> call = trelloApi.getCards(listId, TrelloApi.KEY, fields);

        // Make the request
        call.enqueue(new Callback<ArrayList<TrelloCard>>() {

            @Override
            public void onResponse(Response<ArrayList<TrelloCard>> response, Retrofit retrofit) {
                trelloCards = response.body();
                // Update data in custom view adapter
                objectiveAdapter.updateData(trelloCards);
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
