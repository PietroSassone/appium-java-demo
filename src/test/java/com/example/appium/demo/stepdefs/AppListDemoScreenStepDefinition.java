package com.example.appium.demo.stepdefs;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;

import static com.example.appium.demo.screens.BaseScreenInteractions.PAGE_LOAD_MAXIMUM_WAIT;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.appium.demo.factory.AppiumDriverFactory;
import com.example.appium.demo.screens.list.ListDemoScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;

public class AppListDemoScreenStepDefinition implements En {

    private AppiumDriver<AndroidElement> driver;
    private ListDemoScreen listDemoScreen;

    @Autowired
    private AppiumDriverFactory appiumDriverFactory;

    public AppListDemoScreenStepDefinition() {

        Before((Scenario scenario) -> {
            driver = appiumDriverFactory.getDriver();
            listDemoScreen = new ListDemoScreen(driver);
        });

        Given("^the list screen is available from the app home screen$", () -> await().atMost(PAGE_LOAD_MAXIMUM_WAIT).until(() -> listDemoScreen.getListScreen().isDisplayed(), is(true)));

        When("^the list screen is opened", () -> listDemoScreen.getListScreen().click());

        When("^the user swipes down the screen using a finger", () -> listDemoScreen.swipeUpwards());

        Then("^an element from the bottom of the list should be visible on the screen", () -> {
            await().atMost(PAGE_LOAD_MAXIMUM_WAIT).until(() -> listDemoScreen.getStratusListElement().isDisplayed(), is(true));
        });
    }
}
