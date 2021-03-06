Then(/^I should see the Objective of each OKR in (Q\d\s?\d*)$/) do |quarter|
  trello_objectives = @trello.get_objectives(@trello.get_list_id(quarter))
  @android.wait_for_progress_to_complete
  app_objectives = @screen.objectives.get_objectives
  # Does not scroll the list to view everything so will fail if the Trello list
  # has more cards that can fit on the screen.
  unless app_objectives == trello_objectives
    puts 'Saw this on Trello:  ' + trello_objectives.to_s
    puts 'Saw this in the app: ' + app_objectives.to_s
    raise 'Objectives displayed incorrectly in app'
  end
end

Then(/^I should see the score of each OKR in (Q\d\s?\d*)$/) do |quarter|
  trello_scores = @trello.get_objective_scores(@trello.get_list_id(quarter))
  @android.wait_for_progress_to_complete
  app_scores = @screen.objectives.get_scores
  # Does not scroll the list to view everything so will fail if the Trello list
  # has more cards that can fit on the screen.
  unless app_scores == trello_scores
    puts 'Saw this on Trello:  ' + trello_scores.to_s
    puts 'Saw this in the app: ' + app_scores.to_s
    raise 'Scores displayed incorrectly in app'
  end
end

When(/^I select the first Objective$/) do
  @screen.objectives.select_first_objective
end
