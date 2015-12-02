Then(/^I should see the names of each list from the Trello board$/) do
  expected_list = @trello.list_names
  @base.wait_for_progress_to_complete
  list_displayed = @base.list_items
  # Does not scroll the list to view everything so will fail if the Trello
  # board has more lists that can fit on the screen.
  unless list_displayed == expected_list
    puts 'Saw this on Trello:  ' + expected_list.to_s
    puts 'Saw this in the app: ' + list_displayed.to_s
    raise 'List displayed wrong items'
  end
end