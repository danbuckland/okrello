Then(/^I should see the names of each list from the Trello board$/) do
  wait_for_element_does_not_exist("android.widget.ProgressBar")
  list_displayed = query("ListView AppCompatTextView", :text)
  expected_list = ["Q1 2015", "Q2 2015", "Q3 2015", "Q4 2015"]
  unless list_displayed == expected_list
    puts list_displayed
    raise 'List displayed wrong items'
  end
end
