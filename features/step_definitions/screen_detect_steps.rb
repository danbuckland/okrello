# Step definitions for all steps that simply check which screen is displayed
Given(/^I am on the Main screen of the app$/) do
  @screens.main.await
end
