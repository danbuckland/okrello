package com.blocksolid.okrello.model;

import android.graphics.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dan Buckland on 02/12/2015.
 */
public class TrelloCheckItem {

    private String id;
    private String name;

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

    public String getKeyResult() {
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

}
