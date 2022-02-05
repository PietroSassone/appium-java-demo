package com.example.appium.demo.screens;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.springframework.test.context.ContextConfiguration;

import com.example.appium.demo.config.SpringConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

@ContextConfiguration(classes = SpringConfig.class)
public class BaseScreenInteractions {
    public static final Duration PAGE_LOAD_MAXIMUM_WAIT = Duration.ofSeconds(20);

    private static final Duration SWIPE_DURATION = Duration.ofSeconds(2);
    private static final int SWIPE_END_COORDINATE = 600;

    private final AppiumDriver<AndroidElement> driver;

    public BaseScreenInteractions(final AppiumDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void swipeUpwards() {
        final Dimension currentAppWindowSize = driver.manage().window().getSize();
        final int scrollStartXCoordinate = currentAppWindowSize.getWidth() / 2;
        final int scrollStartYCoordinate = currentAppWindowSize.getHeight() - 100;

        new TouchAction(driver)
            .press(PointOption.point(scrollStartXCoordinate, scrollStartYCoordinate))
            .waitAction(waitOptions(SWIPE_DURATION))
            .moveTo(PointOption.point(scrollStartXCoordinate, SWIPE_END_COORDINATE))
            .release()
            .perform();
    }

}
