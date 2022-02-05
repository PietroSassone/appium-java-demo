@LoginScreen @AndroidApp
Feature: Testing the App under test's login screen

  Scenario: Simple happy case for the login
    Given the login screen is available from the app home screen
    When the login screen is opened
      And the username is set to alice
      And the password is set to mypassword
    When the login button is clicked
    Then the login should be successful
