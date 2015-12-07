package com.blocksolid.okrello.display;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.lang.Exception;

public class ScoresTest {
    Scores scores = new Scores();

    // Tests for getScore method
    // Positive tests for the 11 possible scores
    @Test
    public void getScore_typicalStringEndingIn0point0_returns0point0() throws Exception {
        // A string containing "[0.0]" should return a score of "0.0"
        String string = "This is a typical card title structure [0.0]";
        String result = scores.getScore(string);
        assertEquals("0.0", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point1_returns0point1() throws Exception {
        // A string containing "[0.1]" should return a score of "0.1"
        String string = "This is a typical card title structure [0.1]";
        String result = scores.getScore(string);
        assertEquals("0.1", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point2_returns0point2() throws Exception {
        // A string containing "[0.2]" should return a score of "0.2"
        String string = "This is a typical card title structure [0.2]";
        String result = scores.getScore(string);
        assertEquals("0.2", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point3_returns0point3() throws Exception {
        // A string containing "[0.3]" should return a score of "0.3"
        String string = "This is a typical card title structure [0.3]";
        String result = scores.getScore(string);
        assertEquals("0.3", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point4_returns0point4() throws Exception {
        // A string containing "[0.4]" should return a score of "0.4"
        String string = "This is a typical card title structure [0.4]";
        String result = scores.getScore(string);
        assertEquals("0.4", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point5_returns0point5() throws Exception {
        // A string containing "[0.5]" should return a score of "0.5"
        String string = "This is a typical card title structure [0.5]";
        String result = scores.getScore(string);
        assertEquals("0.5", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point6_returns0point6() throws Exception {
        // A string containing "[0.6]" should return a score of "0.6"
        String string = "This is a typical card title structure [0.6]";
        String result = scores.getScore(string);
        assertEquals("0.6", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point7_returns0point7() throws Exception {
        // A string containing "[0.7]" should return a score of "0.7"
        String string = "This is a typical card title structure [0.7]";
        String result = scores.getScore(string);
        assertEquals("0.7", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point8_returns0point8() throws Exception {
        // A string containing "[0.8]" should return a score of "0.8"
        String string = "This is a typical card title structure [0.8]";
        String result = scores.getScore(string);
        assertEquals("0.8", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point9_returns0point9() throws Exception {
        // A string containing "[0.9]" should return a score of "0.9"
        String string = "This is a typical card title structure [0.9]";
        String result = scores.getScore(string);
        assertEquals("0.9", result);
    }

    @Test
    public void getScore_typicalStringEndingIn1point0_returns1point0() throws Exception {
        // A string containing "[1.0]" should return a score of "1.0"
        String string = "This is a typical card title structure [1.0]";
        String result = scores.getScore(string);
        assertEquals("1.0", result);
    }


    // Tests for numbers above 1.0 that should be rounded down to 1.0
    @Test
    public void getScore_typicalStringEndingIn1point1_returns1point0() throws Exception {
        // A string containing "[1.1]" should return a score of "1.0"
        String string = "This is a typical card title structure with an invalid score of [1.1]";
        String result = scores.getScore(string);
        assertEquals("1.0", result);
    }

    @Test
    public void getScore_typicalStringEndingIn9point0_returns1point0() throws Exception {
        // A string containing "[9.0]" should return a score of "1.0"
        String string = "This is a typical card title structure with an invalid score of [9.0]";
        String result = scores.getScore(string);
        assertEquals("1.0", result);
    }

    @Test
    public void getScore_typicalStringEndingIn9point9_returns1point0() throws Exception {
        // A string containing "[9.9]" should return a score of "1.0"
        String string = "This is a typical card title structure with an invalid score of [9.9]";
        String result = scores.getScore(string);
        assertEquals("1.0", result);
    }

    // Tests for numbers more than 1 digit that should return "!"
    @Test
    public void getScore_typicalStringEndingIn10point0_returnsExclamation() throws Exception {
        // A string containing "[10.0]" should return a score of "!"
        String string = "This is a typical card title structure with an invalid score of [10.0]";
        String result = scores.getScore(string);
        assertEquals("!", result);
    }


    @Test
    public void getScore_typicalStringEndingIn9999999999point9_returnsExclamation() throws Exception {
        // A string containing "[9999999999.9]" should return a score of "!"
        String string = "This is a typical card title structure with an invalid score of [9999999999.9]";
        String result = scores.getScore(string);
        assertEquals("!", result);
    }

    // Tests for numbers with more than 1 number after decimal place
    @Test
    public void getScore_typicalStringEndingIn0point01_returnsExclamation() throws Exception {
        // A string containing "[0.01]" should return a score of "!"
        String string = "This is a typical card title structure with an invalid score of [0.01]";
        String result = scores.getScore(string);
        assertEquals("!", result);
    }

    @Test
    public void getScore_typicalStringEndingIn0point11_returnsExclamation() throws Exception {
        // A string containing "[0.11]" should return a score of "!"
        String string = "This is a typical card title structure with an invalid score of [0.11]";
        String result = scores.getScore(string);
        assertEquals("!", result);
    }

    @Test
    public void getScore_typicalStringEndingIn9point9999999999_returnsExclamation() throws Exception {
        // A string containing "[9.9999999999]" should return a score of "!"
        String string = "This is a typical card title structure with an invalid score of [9.9999999999]";
        String result = scores.getScore(string);
        assertEquals("!", result);
    }

    // Tests for strings that have more than one score in the valid format
    @Test
    public void getScore_stringWithTwoScores_returnsFirstScore() throws Exception {
        // A string containing two valid scores should only return the first valid score
        String string = "This card title has two scores [0.5] and [0.1]";
        String result = scores.getScore(string);
        assertEquals("0.5", result);
    }

    @Test
    public void getScore_stringWithThreeScores_returnsFirstScore() throws Exception {
        // A string containing two valid scores should only return the first valid score
        String string = "[1.0] This card title has three scores one at the start plus [0.5] and [0.1]";
        String result = scores.getScore(string);
        assertEquals("1.0", result);
    }

    // Tests for strings that have no score
    @Test
    public void getScore_stringWithNoScore_returnsExclamation() throws Exception {
        // A string containing two valid scores should only return the first valid score
        String string = "This card title has no score";
        String result = scores.getScore(string);
        assertEquals("!", result);
    }

    // Tests for strings that have just a score but nothing else
    @Test
    public void getScore_scoreOnlyString_returnsScore() throws Exception {
        // A string containing only a score should still return a score
        String string = "[0.5]";
        String result = scores.getScore(string);
        assertEquals("0.5", result);
    }



    // Tests for getGoal method
    // Positive tests for 4 common goal title structures
    @Test
    public void getGoal_stringWithSpaceAndScoreAppended_returnsString() throws Exception {
        // A string containing only a score should still return a score
        String string = "This is a typical card title structure with space [0.0]";
        String result = scores.getGoal(string);
        assertEquals("This is a typical card title structure with space", result);
    }

    @Test
    public void getGoal_stringWithScoreAppended_returnsString() throws Exception {
        // A string containing only a score should still return a score
        String string = "This is a typical card title structure without space[0.0]";
        String result = scores.getGoal(string);
        assertEquals("This is a typical card title structure without space", result);
    }

    @Test
    public void getGoal_stringWithScoreAndSpacePrepended_returnsString() throws Exception {
        // A string containing only a score should still return a score
        String string = "[0.0] This is an alternative card title structure with prepended score and space";
        String result = scores.getGoal(string);
        assertEquals("This is an alternative card title structure with prepended score and space", result);
    }

    @Test
    public void getGoal_stringWithScorePrepended_returnsString() throws Exception {
        // A string containing only a score should still return a score
        String string = "[0.0]This is an alternative card title structure with prepended score without space";
        String result = scores.getGoal(string);
        assertEquals("This is an alternative card title structure with prepended score without space", result);
    }
}