Then(/^I should see the Key Results for the first Objective in (Q\d\s?\d*)$/) do |quarter|
  @screen.key_results.await
  first_objective = @trello.get_first_card_id(@trello.get_list_id(quarter))
  trello_key_results = @trello.get_key_results(first_objective)
  @base.wait_for_progress_to_complete
  app_key_results = @screen.key_results.get_key_results
  # Does not scroll the list to view everything so will fail if the Trello list
  # has more cards that can fit on the screen.
  unless app_key_results == trello_key_results
    puts 'Saw this on Trello:  ' + trello_key_results.to_s
    puts 'Saw this in the app: ' + app_key_results.to_s
    raise 'Key Results displayed incorrectly in app'
  end
end

Then(/^I should see the scores of each Key Result for the first Objective in (Q\d\s?\d*)$/) do |quarter|
  @screen.key_results.await
  first_objective = @trello.get_first_card_id(@trello.get_list_id(quarter))
  trello_scores = @trello.get_key_results_scores(first_objective)
  @base.wait_for_progress_to_complete
  app_scores = @screen.key_results.get_scores
  # Does not scroll the list to view everything so will fail if the Trello list
  # has more cards that can fit on the screen.
  unless app_scores == trello_scores
    puts 'Saw this on Trello:  ' + trello_scores.to_s
    puts 'Saw this in the app: ' + app_scores.to_s
    raise 'Scores displayed incorrectly in app'
  end
end
