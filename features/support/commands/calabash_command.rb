require_relative 'blocking_command'

class CalabashCommand < BlockingCommand

  def initialize profile

    @cmd = 'calabash-android run app/build/outputs/apk/app-debug.apk -c'
    @cmd = @cmd + ' -p ' + profile unless profile.nil?

  end
end
