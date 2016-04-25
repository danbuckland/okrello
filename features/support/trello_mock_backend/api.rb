require 'json'
require 'httparty'

module TrelloMockBackend

  class API

    # Initialises internal variables of the mock server.
    # Call before and after every scenario to be sure there are no leftovers from previous tests.
    def self.reset
      HTTParty.get(Server.url + '/reset')
    end

    # Sets the JSON response for the ```/boards/*/lists``` end point.
    # @param file_name [String] Name of the JSON file to be returned
    def self.set_quarters(file_name:)
      HTTParty.post(Server.url + '/quarters', body: {filename: file_name})
    end

  end

end
