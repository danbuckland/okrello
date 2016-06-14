require_relative 'features/support/trello_mock_backend/boot'
require_relative 'features/support/trello_mock_backend/server'
require_relative 'features/support/commands/calabash_command'
require_relative 'features/support/commands/gradle_build_command'
require_relative 'features/support/commands/launch_emulator_command'

task :default => :cucumber

desc 'Starts the server with logging'
task :start_server, [:block] do |t, args|

  TrelloMockBackend::Boot.boot

  unless args[:block] && args[:block] == 'false'
    block
  end
end

desc 'Starts the server without logging'
task :start_server_quietly, [:block] do |t, args|

  TrelloMockBackend::Boot.boot true

  unless args[:block] && args[:block] == 'false'
    block
  end
end

desc 'Runs Cucumber via Calabash with an optional profile'
task :cucumber, [:profile] => [:build] do |t, args|

  profile = args[:profile]
  Rake::Task[:start_server_quietly].invoke('false')

  puts "Running Cucumber with #{profile} profile" unless profile.nil?

  CalabashCommand.new(profile).execute

end

desc 'Builds the Okrello Android application using Gradle'
task :build do

  puts "Building Android app with Gradle"

  GradleBuildCommand.new.execute
end

desc 'Launches the Android emulator'
task :start_emulator do |t, args|

  puts "Launching Android emulator"

  LaunchEmulatorCommand.new.execute

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
