package org.ex.pages.pages;

import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ModulePage extends BasePage {

    @FindBy(xpath = "//small[text() = 'Название']/following-sibling::input[@class = 'form-control ']")
    private WebElement textArea;

    @FindBy(xpath = "(((//tr)[2])//span)[2]")
    private WebElement moduleId;

    @FindBy(xpath = "(//input[@placeholder = 'Поиск ...'])[2]")
    private WebElement questionIdField;

    @FindBy(xpath = "(//button[text()='Переместить по Id'])[1]")
    private WebElement moveByIdBTN;

    public ModulePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public ModulePage addNewClick() {
        clickOnAddButton();
        return this;
    }

    public String getId() {
        waitLoading();
        return moduleId.getText();
    }

    public ModulePage createModule(String moduleName) {
        waitIt.until(ExpectedConditions
                .visibilityOf(textArea)).sendKeys(moduleName);
        clickOnCreateButton();
        return this;
    }

    public ModulePage createModule(String moduleName, String questionId) {
        waitIt.until(ExpectedConditions
                .visibilityOf(textArea)).sendKeys(moduleName);
        questionIdField.sendKeys(questionId);
        moveByIdBTN.click();
        clickOnCreateButton();

        return this;
    }






}
