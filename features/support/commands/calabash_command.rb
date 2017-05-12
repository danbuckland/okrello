require_relative 'blocking_command'

class CalabashCommand < BlockingCommand

  def initialize profile

    @cmd = 'calabash-android run $APK_PATH -f Res::Formatters::RubyCucumber2 -o \"$HIVE_RESULTS/out.res\" -f pretty'
    @cmd = @cmd + ' -p ' + profile unless profile.nil?

  end
end
