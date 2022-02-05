package com.example.appium.demo.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;

@Getter
public class AppiumProPage {

    private static final String APPIUM_PRO_PAGE_URL = "https://appiumpro.com";

    @FindBy(id = "toggleMenu")
    private AndroidElement toggleMenu;

    @FindBy(css = "ul > li:nth-child(6) > a")
    private AndroidElement contactMenuElement;

    @FindBy(id = "contactEmail")
    private AndroidElement contactEmailInput;

    @FindBy(id = "contactText")
    private AndroidElement contactTextInput;

    @FindBy(xpath = "//input[@value='Send']")
    private AndroidElement sendButton;

    @FindBy(css = "form > div:nth-child(1)")
    private AndroidElement responseMessage;

    private final AppiumDriver<AndroidElement> driver;

    public AppiumProPage(final AppiumDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void navigateToThePage() {
        driver.get(APPIUM_PRO_PAGE_URL);
    }
}
