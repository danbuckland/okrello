package com.blocksolid.okrello.display;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.lang.Exception;

public class ScoresTest {
    Scores scores = new Scores();

    // Tests for extractScoreFromString method

    @Test
    public void extractScoreFromString_typicalStringEndingIn0point0_returns0point0() throws Exception {
        // A string containing "[0.0]" should return a score of "0.0"
        String string = "This is a typical card title structure [0.0]";
        String result = scores.extractScoreFromString(string);
        assertEquals("0.0", result);
    }

}