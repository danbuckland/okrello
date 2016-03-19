# Methods exclusive to the Key Results screen go here
class KeyResultsScreen < App

  def trait
    "RecyclerView id:'keyres_recycler_key_results'"
  end

  def await
    wait_for_element_exists(trait, :timeout => 5, :screenshot_on_error => false)
  end

  def get_key_results
    query("RecyclerView AppCompatTextView id:'list_key_result'", :text)
  end

  def get_scores
    # Currently repeated in ObjectivesScreen class but might need to be made view
    # specific when UI changes.
    query("RecyclerView AppCompatTextView id:'list_score'", :text)
  end

end
