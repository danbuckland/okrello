# Methods exclusive to the Key Results screen go here
class KeyResultsScreen < Base

  def trait
    "ListView id:'keyres_list_key_results'"
  end

  def await
    wait_for_element_exists(trait)
  end

  def get_key_results
    query("ListView AppCompatTextView id:'list_key_result'", :text)
  end

  def get_scores
    # Currently repeated in ObjectivesScreen class but might need to be made view
    # specific when UI changes.
    query("ListView AppCompatTextView id:'list_score'", :text)
  end

end
