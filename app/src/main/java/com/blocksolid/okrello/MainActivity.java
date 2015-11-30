package com.blocksolid.okrello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ProgressBar progressBar;
    public static Button getListsBtn;
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
        // TODO get list names to an array of strings
        // Manually created array for the purpose of testing

        // Populate list view with strings from array
        ArrayList<String> trelloListsArray = new ArrayList<>();
        trelloListsArray.add("Q1 2015");
        trelloListsArray.add("Q2 2015");
        trelloListsArray.add("Q3 2015");
        trelloListsArray.add("Q4 2015");

        listView.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, trelloListsArray));

        // Hide progress indicator when done
        progressBar.setVisibility(View.INVISIBLE);

    }
}
