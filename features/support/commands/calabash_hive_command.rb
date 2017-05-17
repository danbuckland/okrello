require_relative 'blocking_command'

class CalabashHiveCommand < BlockingCommand

  def initialize profile

    @cmd = 'calabash-android run app/build/outputs/apk/app-debug.apk -f pretty'
    @cmd = @cmd + ' -p ' + profile unless profile.nil?

  end
end
