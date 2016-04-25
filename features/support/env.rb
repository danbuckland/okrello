require 'calabash-android/cucumber'
require 'calabash-android/abase'
require 'httparty'
require_relative 'trello_mock_backend/server'

include TrelloMockBackend

KEY = 'cf2308ac2c68ab9a54037478108439e4'
BOARD_ID = '5RMq1Nyb'
