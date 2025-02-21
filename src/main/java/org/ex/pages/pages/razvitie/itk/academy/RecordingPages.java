package org.ex.pages.pages.razvitie.itk.academy;

import lombok.extern.slf4j.Slf4j;
import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class RecordingPages extends BasePage {

    @FindBy(xpath = "//div[text() = 'Maven']")
    private WebElement maven;

    @FindBy(xpath = "//button[text() = 'Проверить знания']")
    private WebElement recButton;


    public RecordingPages(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    public void checkRecording() {
        maven.click();
        recButton.click();
        log.error("А вот тут ОШИБКА!!! так как https://razvitie.itk.academy/main - на старом движке:(");

    }
}
