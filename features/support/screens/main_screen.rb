# Methods exclusive to the Main screen go here
class MainScreen < Base

  def trait
    'MainActivity'
  end

  def await
    wait_for_activity(trait)
  end

end
