# Methods exclusive to the Objectives screen go here
class ObjectivesScreen < App

  def trait
    "RecyclerView id:'objs_recycler_objectives'"
  end

  def await
    wait_for_element_exists(trait, :timeout => 5, :screenshot_on_error => false)
  end

  def get_objectives
    query("RecyclerView AppCompatTextView id:'list_objective'", :text)
  end

  def get_scores
    query("RecyclerView AppCompatTextView id:'list_objs_score'", :text)
  end

  def select_first_objective
    tap_when_element_exists("RecyclerView AppCompatTextView id:'list_objective' index:0")
  end

end
