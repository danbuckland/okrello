require_relative 'non_blocking_command'

class LaunchEmulatorCommand < NonBlockingCommand
  def initialize

    @cmd = 'emulator -netdelay none -netspeed full -no-boot-anim -avd Nexus_5X_API_23'

  end
end
