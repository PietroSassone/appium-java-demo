package com.example.appium.demo.util;

import static org.openqa.selenium.OutputType.FILE;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.TakesScreenshot;
import org.springframework.stereotype.Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java8.Scenario;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class to capture screenshots from the Appium driver and add them to the test report.
 */
@Slf4j
@Component
public class ScreenshotSaver {

    public void addScreenshotToCucumberScenario(final Scenario scenario, final AppiumDriver<AndroidElement> driver) {

        try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream()) {

            ImageIO.write(ImageIO.read(takeScreenshot(driver)), "png", arrayOutputStream);

            scenario.attach(
                    arrayOutputStream.toByteArray(),
                    "image/png",
                    "test_result image"
            );

            log.info("Screenshot was attached to the cucumber scenario result.");
        } catch (Exception e) {
            log.warn("Can't add screenshot to the scenario. Exception: " + e.getMessage());
        }
    }

    private File takeScreenshot(final AppiumDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(FILE);
    }
}
