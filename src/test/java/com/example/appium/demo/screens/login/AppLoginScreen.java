package com.example.appium.demo.screens.login;

import com.example.appium.demo.screens.BaseScreenInteractions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;

@Getter
public class AppLoginScreen extends BaseScreenInteractions {

    @AndroidFindBy(accessibility = "Login Screen")
    private AndroidElement loginScreen;

    @AndroidFindBy(accessibility = "username")
    private AndroidElement userNameInput;

    @AndroidFindBy(accessibility = "password")
    private AndroidElement passwordInput;

    @AndroidFindBy(accessibility = "loginBtn")
    private AndroidElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'You are logged in')]")
    private AndroidElement loggedInMessage;

    public AppLoginScreen(final AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
}
