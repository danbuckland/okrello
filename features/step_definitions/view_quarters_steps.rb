Then(/^I should see the names of each list from the Trello board$/) do
  expected_list = @trello.list_names
  wait_for_element_does_not_exist("android.widget.ProgressBar")
  list_displayed = query("ListView AppCompatTextView", :text)
  # Does not scroll the list to view everything so will fail if the Trello
  # board has more lists that can fit on the screen.
  unless list_displayed == expected_list
    puts 'Saw this on Trello:  ' + expected_list.to_s
    puts 'Saw this in the app: ' + list_displayed.to_s
    raise 'List displayed wrong items'
  end
end
