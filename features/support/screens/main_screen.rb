# Methods exclusive to the Main screen go here
class MainScreen < Base

  def trait
    'MainActivity'
  end

  def await
    wait_for_element_exists(trait, :timeout => 5, :screenshot_on_error => false)
  end

  def select_quarter(quarter)
    tap_when_element_exists("* marked:'#{quarter}'")
  end

end
