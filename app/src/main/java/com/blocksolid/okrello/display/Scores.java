package com.blocksolid.okrello.display;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dan Buckland on 02/12/2015.
 */
public class Scores {
    public String extractScoreFromString(String string) {
        Pattern pattern = Pattern.compile("\\[(\\d{1}\\.\\d{1})\\]");
        Matcher m = pattern.matcher(string);
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

    public String removeScoreFromString(String string) {
        return "";
    }
}
