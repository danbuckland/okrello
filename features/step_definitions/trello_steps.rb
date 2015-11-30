# Step definitions for anything to do with setting up Trello or checking Trello
#
# This will probably be replaced with a mock backend in future so the app side
# tests can be run without requiring a Trello board to be setup.

Given(/^I have a Trello board with at least one list$/) do
  # use a Get request to check that the public test board has lists
  response = HTTParty.get("#{BASE_URL}boards/#{BOARD_ID}/lists?key=#{KEY}")
  no_of_lists = response.size
  raise 'No lists on Trello board' unless no_of_lists > 0
end
