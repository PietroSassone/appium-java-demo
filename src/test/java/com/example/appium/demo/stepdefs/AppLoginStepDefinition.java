package com.example.appium.demo.stepdefs;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static com.example.appium.demo.screens.BaseScreenInteractions.PAGE_LOAD_MAXIMUM_WAIT;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.appium.demo.factory.AppiumDriverFactory;
import com.example.appium.demo.screens.login.AppLoginScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;

public class AppLoginStepDefinition implements En {

    private AppiumDriver<AndroidElement> driver;
    private AppLoginScreen loginScreen;
    private String testUserName;

    @Autowired
    private AppiumDriverFactory appiumDriverFactory;

    public AppLoginStepDefinition() {

        Before((Scenario scenario) -> {
            driver = appiumDriverFactory.getDriver();
            loginScreen = new AppLoginScreen(driver);
        });

        Given("^the login screen is available from the app home screen$", () ->
            await().atMost(PAGE_LOAD_MAXIMUM_WAIT).until(
                () -> loginScreen.getLoginScreen().isDisplayed(), is(true)
            ));

        When("^the login screen is opened", () -> loginScreen.getLoginScreen().click());

        When("^the username is set to (.*)$", (final String userName) -> {
            testUserName = userName;
            loginScreen.getUserNameInput().sendKeys(userName);
        });

        When("^the password is set to (.*)$", (final String password) -> loginScreen.getPasswordInput().sendKeys(password));

        When("^the login button is clicked$", () -> loginScreen.getLoginButton().click());

        Then("^the login should be successful$", () -> {
            await().atMost(PAGE_LOAD_MAXIMUM_WAIT).until(() -> loginScreen.getLoggedInMessage().isDisplayed(), is(true));

            assertThat(loginScreen.getLoggedInMessage().getText(), equalTo(String.format("You are logged in as %s", testUserName)));
        });
    }

}
