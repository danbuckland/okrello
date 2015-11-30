@view_quarters
Feature: View quarters

As a member of a team using Trello to track OKRs,
I want to see the names of all of the lists on my OKR Trello board,
so that I can later use them to view each quarter's OKRs.

  Background: Launch the application to the Main screen
    Given I have a Trello board with at least one list
    And I am on the Main screen of the app

  Scenario: Get lists from single Trello board
    When I tap 'Get lists'
    Then I should see the names of each list from the Trello board
