package com.example.appium.demo.hooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.appium.demo.factory.AppiumDriverFactory;
import com.example.appium.demo.util.ScreenshotSaver;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;

public class Hooks implements En {

    @Autowired
    private AppiumDriverFactory appiumDriverFactory;

    @Autowired
    private ScreenshotSaver screenshotSaver;

    @Value("${useBrowserStack:false}")
    private Boolean useBrowserStack;

    public Hooks() {
        Before("@AndroidApp", this::setupAppiumForApp);

        Before("@MobileWeb", this::setupAppiumForMobileWeb);

        After((Scenario scenario) -> {
            if (scenario.isFailed()) {
                screenshotSaver.addScreenshotToCucumberScenario(scenario, appiumDriverFactory.getDriver());
            }
            appiumDriverFactory.shutDownDriver();
        });

    }

    private void setupAppiumForApp() {
        setUpAppiumDriver(true);
    }

    private void setupAppiumForMobileWeb() {
        setUpAppiumDriver(false);
    }

    private void setUpAppiumDriver(final boolean useMobileApp) {
        if (useBrowserStack) {
            appiumDriverFactory.setUpAndroidDriverForBrowserstack(useMobileApp);
        } else {
            appiumDriverFactory.setUpAndroidDriverLocal(useMobileApp);
        }
    }
}
