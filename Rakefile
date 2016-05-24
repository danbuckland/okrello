require_relative 'features/support/trello_mock_backend/boot'
require_relative 'features/support/trello_mock_backend/server'
require_relative 'features/support/commands/calabash_command'

task :default => :cucumber

desc 'Starts the server'
task :start_server, [:block] do |t, args|

  TrelloMockBackend::Boot.boot

  unless args[:block] && args[:block] == 'false'
    block
  end
end

desc 'Runs Cucumber via Calabash with an optional profile'
task :cucumber, [:profile] do |t, args|

  profile = args[:profile]

  puts "Running Cucumber with #{profile} profile" unless profile.nil?

  CalabashCommand.new(profile).execute
end

def block

  puts "Waiting here. CTRL + C when you are done."

  while true
    sleep 0.1
    # http://en.wikipedia.org/wiki/Unix_signal
    Signal.trap("INT") do
      exit
    end
  end
end

at_exit do
  TrelloMockBackend::Boot.exit
end
