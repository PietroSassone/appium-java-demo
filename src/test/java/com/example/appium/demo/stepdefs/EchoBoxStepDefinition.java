package com.example.appium.demo.stepdefs;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static com.example.appium.demo.screens.BaseScreenInteractions.PAGE_LOAD_MAXIMUM_WAIT;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.appium.demo.factory.AppiumDriverFactory;
import com.example.appium.demo.screens.echo.EchoBoxScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;

public class EchoBoxStepDefinition implements En {

    private AppiumDriver<AndroidElement> driver;
    private EchoBoxScreen echoBoxScreen;
    private String savedText;

    @Autowired
    private AppiumDriverFactory appiumDriverFactory;

    public EchoBoxStepDefinition() {

        Before((Scenario scenario) -> {
            driver = appiumDriverFactory.getDriver();
            echoBoxScreen = new EchoBoxScreen(driver);
        });

        Given("^the echo box screen is available from the app home screen$", () ->
            await().atMost(PAGE_LOAD_MAXIMUM_WAIT).until(
                () -> echoBoxScreen.getEchoBoxScreen().isDisplayed(), is(true)
            ));

        When("^the echo box screen is opened", () -> echoBoxScreen.getEchoBoxScreen().click());

        When("^the message input is filled with (.*)$", (final String textToType) -> {
            savedText = textToType;
            echoBoxScreen.getMessageInput().sendKeys(textToType);
        });

        When("^the save button is clicked$", () -> echoBoxScreen.getSaveButton().click());

        When("^the \"back to homepage\" button is clicked$", () -> echoBoxScreen.getBackButton().click());

        Then("^the message input should contain the \"(.*)\" placeholder text$", (final String expectedPlaceholderText) ->
            assertThat(echoBoxScreen.getMessageInput().getText(), equalTo(expectedPlaceholderText))
        );

        Then("^the \"Here's what you said before:\" message should be displayed with the saved text$", () -> {
            final String expectedPromptText = "Here's what you said before:";
            final MobileElement yourMessagePrompt = echoBoxScreen.getYourSavedMessagePrompt();

            await().atMost(PAGE_LOAD_MAXIMUM_WAIT).until(yourMessagePrompt::isDisplayed, is(true));

            assertThat(yourMessagePrompt.getText(), equalTo(expectedPromptText));
            assertThat(driver.findElement(MobileBy.AccessibilityId(savedText)).isDisplayed(), is(true));
        });
    }

}
