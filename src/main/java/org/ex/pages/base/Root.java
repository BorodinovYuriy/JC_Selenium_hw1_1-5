package org.ex.pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Root extends BasePage {

    @FindBy(xpath = "//a[@href = '/interviews' and text() = 'Интервью']")
    private WebElement interviewLink;

    @FindBy(xpath = "//a[@href = '/theme-question' and text() = 'Вопросы']")
    private WebElement questionLink;

    @FindBy(xpath = "//a[@href = '/quizes' and text() = 'Квизы']")
    private WebElement quizeLink;

    @FindBy(xpath = "//a[@href = '/course-module' and text() = 'Модули']")
    private WebElement moduleLink;

    public Root(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void clickOnInterviewLink() {
        interviewLink.click();
    }
    public void clickOnQuestionLink() {
        questionLink.click();
    }
    public void clickOnQuizeLink() {
        quizeLink.click();
    }
    public void clickOnModuleLink() {
        moduleLink.click();
    }
}
