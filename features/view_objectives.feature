@view_objectives
Feature: View Objectives

  In Trello, each card represents one OKR for one team member. Individuals set the
  card's title to be the Objective and append the title with "[0.0]" to represent
  the starting score "0.0". As each team member makes progress on their individual
  OKRs, they update the score using an average of the scores from each Key Result.
  The maximum score is "1.0".

  As a member of a team tracking quarterly OKRs on a Trello board,
  I want to view my team's objectives and scores for a particular quarter,
  so that I can see what my team mates and I are aiming for and how we're doing.

  Background: Launch the application to the Main screen
    Given I have a Trello board with a list called "Q1 2016" with at least one OKR
    And I am on the Main screen of the app

  Scenario: View Objectives
    When I view Q1 2016
    Then I should see the Objective of each OKR in Q1 2016

  Scenario: View Objective scores
    When I view Q1 2016
    Then I should see the score of each OKR in Q1 2016
