Then(/^I should see the names of each list from the Trello board$/) do
  response = HTTParty.get('https://api.trello.com/1/boards/5RMq1Nyb/lists?key=cf2308ac2c68ab9a54037478108439e4')
  expected_list = Array.new
  response.each do |item|
    expected_list << item["name"]
  end

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
