package com.example.appium.demo.screens.list;

import com.example.appium.demo.screens.BaseScreenInteractions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ListDemoScreen extends BaseScreenInteractions {

    @AndroidFindBy(accessibility = "List Demo")
    private AndroidElement listScreen;

    // This is not a typo, this really is the element name. The name of a cloud type.
    @AndroidFindBy(accessibility = "Stratus")
    private AndroidElement stratusListElement;

    public ListDemoScreen(final AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
}
