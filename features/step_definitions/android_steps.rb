# Common Android step definitions go here
When(/^I tap.* '([^\"]*)'$/) do |button_name|
  @base.tap_button(button_name)
end
