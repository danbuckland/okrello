# Okrello

An Android app for viewing and tracking OKRs using [Trello](https://trello.com/) as a backend.


## Documentation

You can find more information about this project and follow along with its progress by visiting the [Okrello Development](https://trello.com/b/WLms7hW7/okrello-development) Trello Board.


## Building & Running

Although it should be possible to build and run the project using any Android IDE, the project was built using Android Studio and therefore it's recommended to use Android Studio with the latest Android SDK tools.

##### Setup your Trello Board for tracking OKRs

**Okrello** is aimed at teams and individuals who are currently tracking OKRs through Trello, or who want to start tracking OKRs using Trello. **Okrello** is currently "read-only", i.e. it's not possible to edit OKRs and update scores through the application, therefore you'll need to create and update your OKRs directly through Trello.

The application expects the following Trello board setup:

* Each **List** on the Trello board represents a time period (e.g. a Month or Quarter). Lists can be named whatever makes sense to you and your team.
* Each **Card** in a List represents a single **OKR**.
* The **Card Title** of each Card should be the **Objective** part of the OKR and should be appended or prepended with a score between **0.0** and **1.0** in the format "**[1.0]**".
* Each Card should contain a Checklist titled "Key Results" with **CheckItems** representing individual **Key Results**.
* Each **Key Result** should also be appended or prepended with a score between **0.0** and **1.0** in the format "**[1.0]**".
* Board Members should be assigned to the Cards representing the OKRs they have set.

**Important Note:** Until [Authentication/Sign in](https://trello.com/c/KsMEb6qA) has been implemented, **Okrello** will only work with **Public** boards.

To use the app with your own Trello board, you'll need to change the *BOARD_ID* in the **MainActivity** class to match your Trello board ID:

##### Find and copy your own Board ID

1. In a browser, open your Trello board
2. Copy the unique board code from the URL that comes immediately after https://trello.com/b/  
**e.g.** For the Okrello Development board at https://trello.com/b/WLms7hW7/okrello-development, the unique code is "WLms7hW7"

##### Add your Board ID to the Android project

1. Open the project in Android Studio
2. Open the **MainActivity** class and find the *BOARD_ID* constant
3. Replace the current string, "5RMq1Nyb", with your own copied **Board ID**

## Running Tests

This application was developed following principles of Test Driven Development (TDD) and Behaviour Driven Development (BDD) and has two sets of automated tests:

#### Unit Tests

Unit tests have been written for more complex parts of the application using JUnit 4. If you are familiar with unit testing in Android Studio, the tests should be easy enough to find and run.

All unit tests can be found in the `okrello/app/src/test` directory.

#### Cucumber BDD Tests

All [Cucumber](https://github.com/cucumber/cucumber/) tests have been written in Ruby using [Calabash for Android](https://github.com/calabash/calabash-android). To run, you'll need to have a recent version of Ruby installed. If you're new to Ruby, I'd highly recommend using [RVM](https://rvm.io/) with the latest stable version of Ruby, and [creating](https://rvm.io/gemsets/creating) and [using](https://rvm.io/gemsets/using) a specific Gemset for running the tests.

##### Install required Ruby gems

1. In a terminal, navigate to the project root directory
2. Enter `gem install bundler` to install **Bundler**
3. Enter `bundle install` to install required Ruby gems including **Cucumber** and **Calabash**

##### Test against your local Trello board

The Cucumber tests query a Trello board directly through Trello's API to check that what is displayed in the application matches with what is on the board. If you're testing a version of the app pointing to your own Trello board, you'll need to update the *BOARD_ID* in the **env.rb** file to match with the Board ID your app uses.

Reading through the Cucumber scenarios should give an insight into what makes a suitable test board. The tests will eventually be self contained – either creating and populating a Trello board automatically, or testing against a mock backend to which the application will also point.

##### Run the tests against the build

1. Build the Android project locally
2. Launch an emulator or connect an Android device in debug mode
3. Enter `calabash-android run app/build/outputs/apk/app-debug.apk` to install the built app and run the tests


## Copyright

Copyright (c) 2015 Dan Buckland and Contributors. See LICENSE for details.
