# Methods for checking and manipulating test Trello board
class Trello < Base

  # Return lists from Trello board as JSON
  def get_lists
    HTTParty.get("#{BASE_URL}boards/#{BOARD_ID}/lists?key=#{KEY}")
  end

  # Return list names from Trello baord as an array
  def list_names
    list_names = Array.new
    get_lists.each do |item|
      list_names << item["name"]
    end
    list_names
  end

  # Return number of lists on Trello board
  def no_of_lists
    list_names.size
  end

end
