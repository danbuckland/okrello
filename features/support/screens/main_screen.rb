# Methods exclusive to the Main screen go here
class MainScreen < App

  def trait
    'MainActivity'
  end

  def await
    wait_for_activity(trait)
  end

  def select_quarter(quarter)
    tap_when_element_exists("* marked:'#{quarter}'")
  end

end
