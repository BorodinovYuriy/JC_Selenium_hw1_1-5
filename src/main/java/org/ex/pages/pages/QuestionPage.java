package org.ex.pages.pages;

import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class QuestionPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'w-md-editor-text']/textarea")
    WebElement textArea;

    public QuestionPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }
    public QuestionPage createQuestion(String question){
        waitIt.until(ExpectedConditions
                .visibilityOf(textArea)).sendKeys(question);
        clickOnCreateButton();
        return this;
    }

    public QuestionPage addNewClick() {
        clickOnAddButton();
        return this;
    }
}
