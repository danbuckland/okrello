@view_objectives
Feature: View objectives

As a member of a team using Trello to track OKRs,
I want to select a particular quarter in the app,
so that I can see all of my team's individual objectives for that quarter.

  Background: Launch the application to the Main screen
    Given I have a Trello board with a list called "Q4 2015" with at least one card
    And I am on the Main screen of the app

  Scenario: Get lists from single Trello board
    When I select 'Q4 2015'
    Then I should see the names and scores of each Objective in Q4 2015
