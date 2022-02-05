package com.example.appium.demo.stepdefs;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static com.example.appium.demo.screens.BaseScreenInteractions.PAGE_LOAD_MAXIMUM_WAIT;

import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.appium.demo.factory.AppiumDriverFactory;
import com.example.appium.demo.pages.AppiumProPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;

public class MobileWebStepDefinition implements En {

    private static final String JS_CLICK_SCRIPT = "arguments[0].click();";
    private static final String JS_SCROLL_SCRIPT = "arguments[0].scrollIntoView();";

    private AppiumDriver<AndroidElement> driver;
    private AppiumProPage appiumProPage;

    @Autowired
    private AppiumDriverFactory appiumDriverFactory;

    public MobileWebStepDefinition() {
        Before((Scenario scenario) -> {
            driver = appiumDriverFactory.getDriver();
            appiumProPage = new AppiumProPage(driver);
        });

        Given("^the AppiumPro page is opened in a mobile browser", () -> appiumProPage.navigateToThePage());

        Given("^the toggle menu is visible$", () -> await().atMost(PAGE_LOAD_MAXIMUM_WAIT).until(() -> appiumProPage.getToggleMenu().isDisplayed(), is(true)));

        When("^the toggle menu is opened", () -> clickWithJsExecutor(appiumProPage.getToggleMenu()));

        When("^the Contact menu option is selected", () -> clickWithJsExecutor(appiumProPage.getContactMenuElement()));

        When("^the contact email field is filled with (.*)$", (final String inputEmail) -> appiumProPage.getContactEmailInput().sendKeys(inputEmail));

        When("^the contact text field is filled with (.*)$", (final String inputText) -> appiumProPage.getContactTextInput().sendKeys(inputText));

        When("^the send message button is clicked$", () -> appiumProPage.getSendButton().click());

        Then("^the form validation field should be displayed with text (.*)$", (final String expectedCaptchaMessage) -> {
            final MobileElement formResponseMessage = appiumProPage.getResponseMessage();

            await().atMost(PAGE_LOAD_MAXIMUM_WAIT).until(formResponseMessage::isDisplayed, is(true));

            scrollWithJsExecutor(formResponseMessage);

            assertThat(
                String.format("The form validation field should contain the %s message.", expectedCaptchaMessage),
                formResponseMessage.getText(),
                containsString(expectedCaptchaMessage)
            );
        });
    }

    private void clickWithJsExecutor(final MobileElement element) {
        ((JavascriptExecutor) driver).executeScript(JS_CLICK_SCRIPT, element);
    }

    private void scrollWithJsExecutor(final MobileElement element) {
        ((JavascriptExecutor) driver).executeScript(JS_SCROLL_SCRIPT, element);
    }

}
