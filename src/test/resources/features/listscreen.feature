@ListScreen @AndroidApp
Feature: Testing the App under test's list screen to demonstrate swipe action

  Scenario: Simple happy case for swiping on the list screen
    Given the list screen is available from the app home screen
    When the list screen is opened
      And the user swipes down the screen using a finger
    Then an element from the bottom of the list should be visible on the screen
