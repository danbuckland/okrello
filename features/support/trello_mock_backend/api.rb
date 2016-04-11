require 'json'
require 'httparty'

module TrelloBackend

  class API

    # Returns the latest JSON returned by the server at the ```/boards/*/lists``` end point.
    # @return [JSON]
    def self.get_quarters
      response = HTTParty.get('http://localhost:9292' + '/quarters')
      JSON.parse(response.body)
    end

    # Sets the JSON response for the ```/boards/*/lists``` end point.
    # @param file_name [String] Name of the JSON file to be returned
    def self.set_quarters(file_name:)
      HTTParty.post('http://localhost:9292' + '/quarters', body: {filename: file_name})
    end

  end

end
