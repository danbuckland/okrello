package com.blocksolid.okrello.model;

import android.graphics.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dan Buckland on 02/12/2015.
 */
public class TrelloCheckItem implements Comparable<TrelloCheckItem> {

    private String id;
    private String name;
    private double pos;

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

    public int compareTo(TrelloCheckItem o)
    {
        return new Double(pos).compareTo(o.pos);
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
        String notStarted = "#F44336"; // 0.0
        String makingProgress = "#FF9800"; // 0.1, 0.2
        String gettingThere = "#FDD835"; // 0.3, 0.4
        String thatsMoreLikeIt = "#CDDC39"; // 0.5, 0.6
        String awesome = "#7CB342"; // 0.7, 0.8 and 0.9
        String tooEasy = "#558B2F"; // 1.0

        String scoreString = getScore();
        int color = Color.parseColor(grey);
        if (!scoreString.equals("!")) {
            double score = Double.valueOf(scoreString);
            if (score == 0.0) {
                color = Color.parseColor(notStarted);
            } else if (score < 0.25) {
                color = Color.parseColor(makingProgress);
            } else if (score < 0.45) {
                color = Color.parseColor(gettingThere);
            } else if (score < 0.65) {
                color = Color.parseColor(thatsMoreLikeIt);
            } else if (score < 0.95) {
                color = Color.parseColor(awesome);
            } else {
                color = Color.parseColor(tooEasy);
            }
        }
        return color;
    }

}
