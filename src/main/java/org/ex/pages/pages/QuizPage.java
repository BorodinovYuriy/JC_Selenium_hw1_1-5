package org.ex.pages.pages;

import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QuizPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'w-md-editor-text']/textarea")
    WebElement textArea;

    public QuizPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public QuizPage addNewClick() {
        clickOnAddButton();
        return this;
    }

    public QuizPage createQuiz(String quizName) {
        waitIt.until(ExpectedConditions
                .visibilityOf(textArea)).sendKeys(quizName);
        clickOnCreateButton();
        return this;
    }
}
