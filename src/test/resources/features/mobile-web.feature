@AppiumProPage @MobileWeb
Feature: Testing an input form in a mobile web browser through Appium

  Scenario: Appium interacting with the Contact input form via mobile web
    Given the AppiumPro page is opened in a mobile browser
      And the toggle menu is visible
    When the toggle menu is opened
      And the Contact menu option is selected
      And the contact email field is filled with test@test.com
      And the contact text field is filled with What am I doing here?
      And the send message button is clicked
    Then the form validation field should be displayed with text You must fill out the Captcha box