package org.ex.pages.base;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.ex.config.PropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
@Getter
public class BeforeLogin extends BasePage {
    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public BeforeLogin(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void login() {
        getLoginField().sendKeys(PropertiesLoader.getUsername());
        getPasswordField().sendKeys(PropertiesLoader.getPassword());
        sleepSec(2);//глюки сайта
        getLoginButton().click();
    }
}
