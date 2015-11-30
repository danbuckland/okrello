require 'calabash-android/abase'

# methods shared by all screens go here
class Base < Calabash::ABase

  def tap_button(button_name)
    button_id = $buttons.fetch(button_name, "unnamed_button")
    if button_id != "unnamed_button"
      tap_when_element_exists("* id:'#{button_id}'")
    else
      raise "\"#{button_name}\" is an unknown button. Please update the constants.rb file"
    end
  end

end
