package org.ex.pages.pages;

import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InterviewPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'modalContent afade']//input[@type = 'input']")
    private WebElement createInterviewInput;

    public InterviewPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public InterviewPage addNewClick() {
        clickOnAddButton();
        return this;
    }
    public InterviewPage createNewInterview(String interviewName) {
        waitIt.until(ExpectedConditions
                .visibilityOf(createInterviewInput)).sendKeys(interviewName);
        clickOnCreateButton();
        return this;
    }
}
