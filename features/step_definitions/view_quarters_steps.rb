Then(/^I should see the names of each list from the Trello board$/) do
  list_displayed = query("ListView AppCompatTextView", :text)
  expected_list = ["Q1 2015", "Q2 2015", "Q3 2015", "Q4 2015"]
  raise 'List displayed wrong items' unless list_displayed == expected_list
end
