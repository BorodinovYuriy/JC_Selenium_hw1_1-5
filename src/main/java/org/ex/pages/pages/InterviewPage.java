package org.ex.pages.pages;

import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InterviewPage extends BasePage {
    @FindBy(xpath = "//div[@class = 'table-responsive']//tbody/tr")
    private List<WebElement> rows;
    @FindBy(xpath = "//button[@type = 'button' and text() = '+ Добавить']")
    private WebElement addInterviewButton;
    @FindBy(xpath = "//div[@class = 'modalContent afade']//input[@type = 'input']")
    private WebElement createInterviewInput;
    @FindBy(xpath = "//div[@class = 'modalContent afade']//button[text() = 'Создать']")
    private WebElement createButton;


    public InterviewPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getRows(){
        waitLoading();
        return waitIt.until(ExpectedConditions.visibilityOfAllElements(rows));
    }

    public InterviewPage addNewClick() {
        waitIt.until(ExpectedConditions.visibilityOf(addInterviewButton)).click();
        return this;
    }
    public InterviewPage createNewInterview(String interviewName) {
        waitIt.until(ExpectedConditions
                .visibilityOf(createInterviewInput)).sendKeys(interviewName);
        createButton.click();
        return this;
    }

}
