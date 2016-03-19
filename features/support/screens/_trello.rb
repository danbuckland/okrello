# Methods for checking and manipulating test Trello board
class Trello < Calabash::ABase

  # Return lists from Trello board as JSON
  def get_lists
    HTTParty.get("#{BASE_URL}boards/#{BOARD_ID}/lists?fields=name&key=#{KEY}")
  end

  # Return list names from Trello board as an array
  def get_list_names
    list_names = Array.new
    get_lists.each do |item|
      list_names << item['name']
    end
    list_names
  end

  # Return number of lists on Trello board
  def no_of_lists
    get_list_names.size
  end

  def get_list_id(list_name)
    if get_list_names.include?(list_name)
      get_lists.find {|h| h['name'] == list_name}['id']
    else
      raise "List \"#{list_name}\" does not exist"
    end
  end

  def get_cards_from_list(list_id)
    HTTParty.get("#{BASE_URL}lists/#{list_id}/cards?fields=name&key=#{KEY}")
  end

  def get_card_names_from_list(list_id)
    card_names = Array.new
    get_cards_from_list(list_id).each do |item|
      card_names << item['name']
    end
    card_names
  end

  def no_of_cards_in_list(list_id)
    get_card_names_from_list(list_id).size
  end

  def get_objectives(list_id)
    card_title_array = get_card_names_from_list(list_id)
    card_title_array.map do |card_title|
      card_title.gsub(/\s?\[\d{1}\.\d{1}\]/, "").strip
    end
  end

  def get_objective_scores(list_id)
    card_title_array = get_card_names_from_list(list_id)
    card_title_array.map do |card_title|
      card_title[/\[(\d{1}\.\d{1})\]/, 1]
    end
  end

  def get_first_card_id(list_id)
    first_card = get_cards_from_list(list_id)[0]
    first_card['id']
  end

  def get_checkItems_from_checklist(checklist_id)
    checkItems_response = HTTParty.get("#{BASE_URL}/checklists/#{checklist_id}/checkItems?key=#{KEY}")
    checkItems = Array.new
    checkItems_response.each do |item|
      checkItems << item['name']
    end
    checkItems
  end

  def get_key_results(card_id)
    first_checklist_id = HTTParty.get("#{BASE_URL}/cards/#{card_id}/checklists?fields=id&key=#{KEY}")[0]['id']
    checkItem_array = get_checkItems_from_checklist(first_checklist_id)
    checkItem_array.map do |checkItem_name|
      checkItem_name.gsub(/\s?\[\d{1}\.\d{1}\]/, "").strip
    end
  end

  def get_key_results_scores(card_id)
    first_checklist_id = HTTParty.get("#{BASE_URL}/cards/#{card_id}/checklists?fields=id&key=#{KEY}")[0]['id']
    checkItem_array = get_checkItems_from_checklist(first_checklist_id)
    checkItem_array.map do |checkItem_name|
      checkItem_name[/\[(\d{1}\.\d{1})\]/, 1]
    end
  end

end
