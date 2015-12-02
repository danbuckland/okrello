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

  def wait_for_progress_to_complete
    wait_for_element_does_not_exist("android.widget.ProgressBar")
  end

  def list_items
    query("ListView AppCompatTextView", :text)
  end

end
