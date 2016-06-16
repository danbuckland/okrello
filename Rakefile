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
task :cucumber, [:profile] => [:start_emulator, :build] do |t, args|

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

  no_of_devices = `adb devices`.split("\n").size - 1
  if no_of_devices == 0 # if there are no attached devices
    puts 'Launching Android emulator'

    LaunchEmulatorCommand.new.execute
    sleep 5

    booting = ''
    while booting != 'stopped'
      booting = `adb shell getprop init.svc.bootanim`.strip
      puts 'Waiting for emulator to boot'
      sleep 3
    end
  else
    puts 'Devices already connected or emulator already running'
  end

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
