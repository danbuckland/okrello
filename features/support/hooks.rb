include TrelloMockBackend

# Reset the API before and after each scenario
Before do
  API.reset
end

After do
  API.reset
end
