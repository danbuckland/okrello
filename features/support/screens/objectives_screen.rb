# Methods exclusive to the Objectives screen go here
class ObjectivesScreen < Base

  def trait
    "ListView id:'objs_list_objectives'"
  end

  def await
    wait_for_element_exists(trait, :timeout => 5, :screenshot_on_error => false)
  end

  def get_objectives
    query("ListView AppCompatTextView id:'list_objective'", :text)
  end

  def get_scores
    query("ListView AppCompatTextView id:'list_score'", :text)
  end

  def select_first_objective
    tap_when_element_exists("ListView AppCompatTextView id:'list_objective' index:0")
  end

end
