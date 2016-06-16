##
# Step definitions for anything to do with setting up Trello or checking Trello
#
# This will probably be replaced with a mock backend in future so the app side
# tests can be run without requiring a Trello board to be setup.
##

Given(/^I have a Trello board with at least one list$/) do
  # Server response default should have at least one list but set anyway
  API.set_quarters(file_name: 'quarters')
  # No need to verify the number of lists returned by mock backend, do it anyway
  raise 'No lists on Trello board' unless @trello.no_of_lists > 0
end

Given(/^I have a Trello board with a list called "(.*?)" with at least one OKR$/) do |list_name|
  # Default response should have the appropriate named list but set anyway
  API.set_quarters(file_name: 'quarters')
  # Verify that the correctly named list is present
  list_id = @trello.get_list_id(list_name)
  raise 'No cards in list' unless @trello.no_of_cards_in_list(list_id) > 0
end
