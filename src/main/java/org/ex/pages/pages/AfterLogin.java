package org.ex.pages.pages;

import lombok.extern.slf4j.Slf4j;
import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Slf4j
public class AfterLogin extends BasePage {

    @FindBy(xpath = "//div[@class = 'table-responsive']/table")
    private WebElement table;
    @FindBy(xpath = "//tbody[@class = 'ok']/tr/td/div")
    private List<WebElement> rows;
    @FindBy(xpath = "//a[@href = '/interviews' and text() = 'Интервью']")
    private WebElement interviewLink;

    public AfterLogin(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void waitSetTable(){
        table = waitIt.until(ExpectedConditions.visibilityOf(table));
    }
    public boolean tableIsVisible(){
        return table.isDisplayed();
    }

    public void clickOnInterviewLink() {
        interviewLink.click();
    }

    public boolean checkCreate(List<WebElement> list, String wordContains) {
        return list.stream()
                .anyMatch(
                        webElement -> webElement
                                .getText().contains(wordContains));
    }
}
