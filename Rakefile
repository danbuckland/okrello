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
task :cucumber, [:profile] => [:start_emulator, :change_app_url, :build] do |t, args|

  Rake::Task[:start_server_quietly].invoke('false')
  Rake::Task[:change_app_url].reenable
  Rake::Task[:change_app_url].invoke('prod')

  profile = args[:profile]

  puts "Running Cucumber with #{profile} profile" unless profile.nil?

  CalabashCommand.new(profile).execute

end

desc 'Hive specific Cucumber runner'
task :hive, [:profile] do |t, args|

  Rake::Task[:start_server_quietly].invoke('false')

  profile = args[:profile]

  puts "Running Cucumber with #{profile} profile" unless profile.nil?

  CalabashHiveCommand.new(profile).execute

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

desc 'Change app settings to point to mock backend'
task :change_app_url, [:env] do |t, args|

  if args[:env] == 'prod'
    mock_backend_url = 'https://api.trello.com/1/'
    puts "Reverting app url to production"
  else
    mock_backend_url = TrelloMockBackend::Server.url + '/'
    puts "Pointing app to #{mock_backend_url}"
  end

  file_names = ['app/src/main/java/com/blocksolid/okrello/api/ServiceGenerator.java']

  file_names.each do |file_name|
    text = File.read(file_name)
    new_contents = text.gsub(/(BASE_URL = ")([^\"]*)(")/, '\1' + mock_backend_url + '\3')

    File.open(file_name, "w") {|file| file.puts new_contents}
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
