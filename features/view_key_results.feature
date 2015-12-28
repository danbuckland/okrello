@view_key_results
Feature: View Key Results

In Trello, a single checklist item (checkItem) in a specific checklist
represents each Key Result belonging to an objective. Individuals set the
checkitems to be the Key Result and append each checkitem with "[0.0]" to
represent the starting score "0.0". As progress is made against each Key Result,
individuals update the score appended to each checkitem. The maximum score for
each Key Result is "1.0".

As a member of a team tracking quarterly OKRs on a Trello board,
I want to view my team's Key Results and scores for a particular objective,
so that I can see in detail what my team mates and I are aiming for and how
we're doing.

  Background: View Q1 2016 Objectives
    Given I have a Trello board with a list called "Q1 2016" with at least one OKR
    And I view Q1 2016
