package org.ex.pages.pages;

import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ModulePage extends BasePage {

    @FindBy(xpath = "//small[text() = 'Название']/following-sibling::input[@class = 'form-control ']")
    WebElement textArea;

    public ModulePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public ModulePage addNewClick() {
        clickOnAddButton();
        return this;
    }

    public ModulePage createModule(String moduleName) {
        waitIt.until(ExpectedConditions
                .visibilityOf(textArea)).sendKeys(moduleName);
        clickOnCreateButton();
        return this;
    }
}
