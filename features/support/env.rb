require 'calabash-android/cucumber'
require 'calabash-android/abase'
require 'httparty'
require File.dirname(__FILE__) + '/trello_mock_backend/server'

KEY = 'cf2308ac2c68ab9a54037478108439e4'
BASE_URL = "https://api.trello.com/1/"
BOARD_ID = "5RMq1Nyb"

include TrelloBackend
