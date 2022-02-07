@EchoBoxScreen @AndroidApp
Feature: Testing the App under test's echo box with memory

  Scenario: Simple happy case for the echo box to show it saves text in memory
    Given the echo box screen is available from the app home screen
    When the echo box screen is opened
    Then the message input should contain the "Say something" placeholder text
    When the message input is filled with What am I doing here?
      And the save button is clicked
    Then the "Here's what you said before:" message should be displayed with the saved text
    When the "back to homepage" button is clicked
      And the echo box screen is available from the app home screen
    When the echo box screen is opened
      And the message input should contain the "Say something new" placeholder text
    Then the "Here's what you said before:" message should be displayed with the saved text
