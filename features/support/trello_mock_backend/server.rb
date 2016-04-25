require 'sinatra'
require 'sinatra/json'
require 'json'

require_relative 'utils'

module TrelloMockBackend

  class Server < Sinatra::Base

    @@quarters = nil
    @@objectives = nil
    @@key_results = nil

    get '/' do
      'Trello mock backend running'
    end

    # Effectively reset the server by resetting all responses.
    get '/reset' do
      @@quarters = nil
      @@objectives = nil
      @@key_results = nil
    end

    # Returns the IP address of the local machine.
    # Solution courtesy of ustwo https://github.com/ustwo/bdd-crossplatform-apps
    def self.host
      Socket.ip_address_list.find { |a| a.ipv4? && !a.ipv4_loopback? }.ip_address
    end

    # Return the default port used by rackup.
    def self.port
      9292
    end

    # Returns the URL of the server.
    def self.url
      "http://#{host}:#{port}"
    end

    set :checkItems do
      JSON.parse(File.read('responses/key_results.json'))
    end

    # Calls made by the application

    get '/boards/*/lists' do
      if @@quarters.nil?
        # Default response
        @@quarters = Utils.static_json(file_name: 'quarters')
      else
        @@quarters
      end

      json @@quarters
    end

    get '/lists/*/cards' do
      if @@objectives.nil?
        # Default response
        @@objectives = Utils.static_json(file_name: 'objectives')
      else
        @@objectives
      end

      json @@objectives
    end

    get '/cards/*/checklists' do
      if @@key_results.nil?
        # Default response
        @@key_results = Utils.static_json(file_name: 'key_results')
      else
        @@key_results
      end

      json @@key_results
    end

    # Calls made by the API

    get '/quarters' do
      @@quarters
    end

    get '/objectives' do
      @@objectives
    end

    get '/key_results' do
      @@key_results
    end

    post '/quarters' do
      file_name = params[:filename]
      @@quarters = JSON.parse(File.read("responses/#{file_name}"))
    end

  end

end
