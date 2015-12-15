# Methods exclusive to the Objectives screen go here
class ObjectivesScreen < Base

  def trait
    'ObjectivesActivity'
  end

  def await
    wait_for_activity(trait)
  end

  def get_objectives
    query("ListView AppCompatTextView id:'list_objective'", :text)
  end

  def get_scores
    query("ListView AppCompatTextView id:'list_score'", :text)
  end

end
