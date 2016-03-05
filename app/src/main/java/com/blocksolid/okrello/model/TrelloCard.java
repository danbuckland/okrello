package com.blocksolid.okrello.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dan Buckland on 02/12/2015.
 */
public class TrelloCard {

    private String id;
    private String name;
    private ArrayList<TrelloChecklist> checklists;

    public ArrayList<TrelloChecklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(ArrayList<TrelloChecklist> checklists) {
        this.checklists = checklists;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        // TODO remove this after unit tests have been updated to use a mock backend
        this.name = name;
    }

    public String getScore() {
        Pattern pattern = Pattern.compile("\\[(\\d{1}\\.\\d{1})\\]");
        Matcher m = pattern.matcher(name);
        if (m.find()) {
            String score = m.group(1);
            double value = Double.parseDouble(score);
            if (value > 1) {
                return "1.0";
            } else {
                return score;
            }
        } else {
            return "!";
        }
    }

    public String getObjective() {
        return name.replaceAll("\\s?\\[\\d{1}\\.\\d{1}\\]", "").trim();
    }

    public int getScoreColor() {
        // No neat way to pull colors from colors.xml
        String grey = "#E6E6E6";
        String red = "#FF5722";
        String amber = "#FFC107";
        String green = "#8BC34A";

        String scoreString = getScore();
        int color = Color.parseColor(grey);
        if (!scoreString.equals("!")) {
            double score = Double.valueOf(scoreString);
            if (score < 0.25) {
                color = Color.parseColor(red);
            } else if (score < 0.55) {
                color = Color.parseColor(amber);
            } else {
                color = Color.parseColor(green);
            }
        }
        return color;
    }

    public ArrayList<TrelloCheckItem> getKeyResultsCheckitems() {
        // Initialise keyResults ArrayList as null to be returned if there are no keyResults
        ArrayList<TrelloCheckItem> keyResults = null;
        int position = getKeyResultsChecklistPosition();
        if (position >= 0) { // If a suitable Key Results checklist is found
            // Get checkItems to use as Key Results from that checklist
            keyResults = getChecklists().get(position).getTrelloCheckItems();
        }
        return keyResults;
    }

    public int getKeyResultsChecklistPosition() {
        if (checklists.size() == 1) {
            // If there's only one checklist, use that checklist as Key Results
            return 0;
        } else if (checklists.size() > 1) {
            // When card has more than one checklist belonging to a card
            // only checkItems belonging to the checklist called "Key Results" are displayed.
            for (int i = 0; i < checklists.size(); i++) {
                String checklistName = checklists.get(i).getName();
                if (checklistName.equalsIgnoreCase("Key Results")) {
                    return i;
                }
            }
        }
        // When there is no checklist called "Key Results" belonging to a card with
        // multiple checklists, then no Key Results are displayed.
        return -1;
    }

}
