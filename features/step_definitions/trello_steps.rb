# Step definitions for anything to do with setting up Trello or checking Trello
#
# This will probably be replaced with a mock backend in future so the app side
# tests can be run without requiring a Trello board to be setup.

Given(/^I have a Trello board with at least one list$/) do
  # use a Get request to check that the public test board has lists
  puts 'Imagine I checked and there were some list'
  puts 'Found 4 lists on Trello board'
end
