require 'sinatra'
require 'json'

class TrelloBackend < Sinatra::Base

  set :bind, '0.0.0.0'

  set :lists do
    JSON.parse(File.read('responses/boards/lists/lists.json'))
  end

  get '/boards/*/lists' do
    content_type :json
    TrelloBackend.lists.to_json
  end

  get '/' do
    "Trello mock backend running"
  end
end
