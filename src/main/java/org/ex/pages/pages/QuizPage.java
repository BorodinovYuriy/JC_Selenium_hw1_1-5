package org.ex.pages.pages;

import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QuizPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'w-md-editor-text']/textarea")
    private WebElement textArea;

    @FindBy(xpath = "(//td)[1]")
    private WebElement quizNameLink;

    @FindBy(xpath = "(//button[text()='Сохранить'])[1]")
    private WebElement buttonSave;

    @FindBy(xpath = "//a[@class = 'back-link pull-left']")
    private WebElement backLink;

    @FindBy(xpath = "(//td)[9]")
    private WebElement waitTD;

    @FindBy(xpath = "(//td)[1]")
    private WebElement firstQuizName;

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

    public boolean editQuiz(String randomWords) {
        waitLoading();
        quizNameLink.click();
        textArea.clear();
        textArea.sendKeys(randomWords);
        buttonSave.click();
        backLink.click();
        waitIt.until(ExpectedConditions.visibilityOf(waitTD));

        return randomWords.equals(firstQuizName.getText());
    }
}
