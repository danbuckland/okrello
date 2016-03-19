require 'calabash-android/management/app_installation'

AfterConfiguration do |config|
  FeatureMemory.feature = nil
end

Before do |scenario|
  ##
  # Create page objects which allows methods methods to be called in step
  # definitions the following way:
  #
  #   @android.wait_for_progress_to_complete
  #   @app.clear_settings
  #   @screen.home.await
  #
  ##
  @android ||= page(Android)
  @app     ||= page(App)
	@screen  ||= page(Screens)
  @trello  ||= page(Trello)


  scenario = scenario.scenario_outline if scenario.respond_to?(:scenario_outline)
  feature = scenario.feature

  ##
  # Changes default behaviour to only reinstall on new test runs to encourage
  # separating scenarios into multiple feature files.
  ##
  if FeatureMemory.feature == nil || ENV['RESET_BETWEEN_SCENARIOS'] == '1'
    if ENV['RESET_BETWEEN_SCENARIOS'] == '1'
      log 'New scenario - reinstalling apps'
    else
      log 'First scenario of new test run - reinstalling apps'
    end

    uninstall_apps
    install_app(ENV['TEST_APP_PATH'])
    install_app(ENV['APP_PATH'])
    FeatureMemory.feature = feature
    FeatureMemory.invocation = 1
  else
    FeatureMemory.invocation += 1
  end
end

FeatureMemory = Struct.new(:feature, :invocation).new
