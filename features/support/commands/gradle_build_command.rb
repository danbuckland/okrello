require_relative 'blocking_command'

class GradleBuildCommand < BlockingCommand
  def initialize

    @cmd = './gradlew assembleDebug'

  end
end
