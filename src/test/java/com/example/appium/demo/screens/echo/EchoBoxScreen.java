package com.example.appium.demo.screens.echo;

import com.example.appium.demo.screens.BaseScreenInteractions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;

@Getter
public class EchoBoxScreen extends BaseScreenInteractions {

    @AndroidFindBy(accessibility = "Echo Box")
    private AndroidElement echoBoxScreen;

    @AndroidFindBy(accessibility = "messageInput")
    private AndroidElement messageInput;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"messageSaveBtn\"]")
    private AndroidElement saveButton;

    @AndroidFindBy(className = "android.widget.ImageButton")
    private AndroidElement backButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Here's what you said before:\"]")
    private AndroidElement yourSavedMessagePrompt;

    public EchoBoxScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
}
