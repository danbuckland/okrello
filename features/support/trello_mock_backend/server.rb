require 'sinatra'
require 'json'

module TrelloBackend

  class Server < Sinatra::Base

    def empty_json
      JSON.parse('{}')
    end

    set :bind, '0.0.0.0'

    set :lists do
      JSON.parse(File.read('responses/quarters.json'))
    end

    set :cards do
      JSON.parse(File.read('responses/objectives.json'))
    end

    set :checkItems do
      JSON.parse(File.read('responses/key_results.json'))
    end

    # Calls made by the application

    get '/boards/*/lists' do
      @@quarters = Server.lists.to_json
      @@quarters
    end

    get '/lists/*/cards' do
      content_type :json
      TrelloBackend.cards.to_json
    end

    get '/cards/*/checklists' do
      content_type :json
      TrelloBackend.checkItems.to_json
    end

    get '/' do
      'Trello mock backend running'
    end

    # Calls made by API

    get '/quarters' do
      json @@quarters
    end

    post '/quarters' do
      file_name = params[:filename]
      @@quarters = JSON.parse(File.read("responses/#{file_name}"))
      empty_json
    end

  end

end
