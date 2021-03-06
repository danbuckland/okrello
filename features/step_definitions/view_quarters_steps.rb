Then(/^I should see the name of each quarter that appears on the Trello board$/) do
  expected_list = @trello.get_list_names
  @android.wait_for_progress_to_complete
  list_displayed = @android.list_items
  # Does not scroll the list to view everything so will fail if the Trello
  # board has more lists that can fit on the screen.
  unless list_displayed == expected_list
    puts 'Saw this on Trello:  ' + expected_list.to_s
    puts 'Saw this in the app: ' + list_displayed.to_s
    raise 'App not displaying all quarters'
  end
end

When(/^I view (Q\d\s?\d*)$/) do |quarter|
  @screen.main.select_quarter(quarter)
  @screen.objectives.await
end
