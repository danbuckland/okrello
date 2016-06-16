require_relative 'non_blocking_command'

class LaunchEmulatorCommand < NonBlockingCommand
  def initialize
    # TODO Unless emulator is already running do the following
    @cmd = 'emulator -netdelay none -netspeed full -no-boot-anim -avd Nexus_5X_API_23'

  end
end
