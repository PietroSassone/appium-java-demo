package com.example.appium.demo.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
public class AppiumDriverFactory {

    private static final String APP_UNDER_TEST_URL = "https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.apk";
    private static final String APPIUM_SERVER_LOCAL_URL = "http://localhost:4723/wd/hub";
    private static final String BROWSERSTACK_DRIVER_REMOTE_URL = "http://hub.browserstack.com/wd/hub";
    private static final String APP_UNDER_TEST_BROWSERSTACK_URL = "bs://d843806f7366ab84316221b3d13077562482e027";
    private static final String BROWSERSTACK_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private static final String BROWSERSTACK_ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static final int TEN_SECONDS = 10;

    @Value("${browserStackOSVersion:9.0}")
    private String browserStackOSVersion;

    @Value("${browserStackDevice:Xiaomi Redmi Note 7}")
    private String browserStackDevice;

    private AppiumDriver<AndroidElement> driver;
    private RemoteWebDriver mobileWebDriver;

    public void setUpAndroidDriverLocal(final boolean useMobileApp) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        if (useMobileApp) {
            capabilities.setCapability("app", APP_UNDER_TEST_URL);
        } else {
            capabilities.setCapability("browserName", "chrome");
        }

        try {
            driver = new AppiumDriver<>(new URL(APPIUM_SERVER_LOCAL_URL), capabilities);
            driver.manage().timeouts().implicitlyWait(TEN_SECONDS, TimeUnit.SECONDS);
            log.info("Starting up Appium driver with capabilities: {}", capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to setup local Appium driver.");
        }
    }

    public void setUpAndroidDriverForBrowserstack(final boolean useMobileApp) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set your access credentials
        capabilities.setCapability("browserstack.user", BROWSERSTACK_USERNAME);
        capabilities.setCapability("browserstack.key", BROWSERSTACK_ACCESS_KEY);

        if (useMobileApp) {
            // Set URL of the application under test
            capabilities.setCapability("app", APP_UNDER_TEST_BROWSERSTACK_URL);
        } else {
            capabilities.setCapability("browserName", "chrome");
        }

        // Specify device and os_version for testing
        capabilities.setCapability("device", browserStackDevice);
        capabilities.setCapability("os_version", browserStackOSVersion);

        // Set other BrowserStack capabilities
        capabilities.setCapability("project", "Appium Demo Java Project");
        capabilities.setCapability("build", "browserstack-appium-build-1");
        capabilities.setCapability("name", "appium browserstack test");

        try {
            driver = new AppiumDriver<>(new URL(BROWSERSTACK_DRIVER_REMOTE_URL), capabilities);
            log.info("Starting up Appium driver on BrowserStack with capabilities: {}", capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to setup Appium driver for BrowserStack.");
        }
    }

    public void shutDownDriver() {
        if (driver != null) {
            driver.quit();
            log.info("Closing appium driver session.");
        }
    }
}
