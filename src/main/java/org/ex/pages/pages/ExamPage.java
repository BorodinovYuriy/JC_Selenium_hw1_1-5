package org.ex.pages.pages;

import lombok.extern.slf4j.Slf4j;
import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.ex.utils.base.StaticBaseUtils.FAKER;

@Slf4j
public class ExamPage extends BasePage {
    @FindBy(xpath = "(//tr/td)[9]")
    private WebElement id;

    @FindBy(xpath = "(//input[@placeholder = 'Название'] )[1]")
    private WebElement name;

    @FindBy(xpath = "(//input[@placeholder = 'ID для перемещения'] )[1]")
    private WebElement setId;

    @FindBy(xpath = "(//button[text() = 'Переместить по Id'])[1]")
    private WebElement moveButton;

    @FindBy(xpath = "(//button[text() = 'Сохранить'])[1]")
    private WebElement saveButton;

    @FindBy(xpath = "//a[@class = 'back-link pull-left']")
    private WebElement backLink;

    @FindBy(xpath = "((//tr)[2]//span)[1]")
    private WebElement nameCheck;

    @FindBy(xpath = "//button[text() = '+ Добавить']")
    private WebElement addButton;

    @FindBy(xpath = "//button[text() = 'Создать']")
    private WebElement createButton;



    public ExamPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public boolean editCheck() {
        waitLoading();
        String check = FAKER.lorem().sentence(15);

        this.id.click();

        this.name.clear();
        this.name.sendKeys(check);
        this.saveButton.click();
        this.backLink.click();

        waitLoading();
        sleepSec(1);//без - иногда не успевает
        return checkEquals(check, nameCheck.getText());
    }

    private boolean checkEquals(String check, String text) {
        return text.equals(check);
    }

    public boolean addNewAndCheck(String questionId) {
        waitLoading();

        String check = FAKER.lorem().sentence(15);

        addButton.click();
        this.name.sendKeys(check);
        this.setId.sendKeys(questionId);
        this.moveButton.click();

        this.createButton.click();

        waitLoading();
        return checkEquals(check, nameCheck.getText());



    }


}
