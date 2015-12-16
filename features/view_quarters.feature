@view_quarters
Feature: View quarters

In Trello, each quarter is represented by a single list titled with the quarter
it represents e.g. "Q4 2015", "Q1 2016" etc. These lists are added manually by
the team each quarter with the current/active quarter in the left-most position.

As a member of a team tracking quarterly OKRs on a Trello board,
I want to see each quarter represented in an application on my phone,
so that I can navigate betwwen each quarter's OKRs.

  Background: Launch the application to the Main screen
    Given I have a Trello board with at least one list

  Scenario: View quarters
    When I am on the Main screen of the app
    Then I should see the name of each quarter that appears on the Trello board
