require 'sinatra'
require 'json'

class TrelloBackend < Sinatra::Base

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

  get '/boards/*/lists' do
    content_type :json
    TrelloBackend.lists.to_json
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
end
