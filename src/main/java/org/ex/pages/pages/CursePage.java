package org.ex.pages.pages;

import lombok.extern.slf4j.Slf4j;
import org.ex.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.ex.utils.base.StaticBaseUtils.FAKER;
@Slf4j
public class CursePage extends BasePage {

    @FindBy(xpath = "//button[text() = '+ Добавить']")
    private WebElement addButton;

    @FindBy(xpath = "(//input[@placeholder='Название'])[1]")
    private WebElement name;

    @FindBy(xpath = "//button[text()='+ Модуль ']")
    private WebElement plusModule;

    @FindBy(xpath = "(//input[@placeholder='Название'])[2]")
    private WebElement moduleName;

    @FindBy(xpath = "//input[@placeholder='Модуль']")
    private WebElement moduleAddId;

    @FindBy(xpath = "//button[text() = 'Создать']")
    private WebElement createButton;

    @FindBy(xpath = "(//td)[1]/span")
    private WebElement curseCreatedName;

    @FindBy(xpath = "//small[text()='Превью курс модуля']/parent::div")
    private WebElement modulePreview;

    public CursePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public boolean perform(String moduleId) {
    addButton.click();
    String moduleNameString = FAKER.lorem().sentence(10);
    name.sendKeys(moduleNameString);
    plusModule.click();
    this.moduleName.sendKeys(FAKER.lorem().sentence(1));
    moduleAddId.sendKeys(moduleId);
    waitIt.until(ExpectedConditions.visibilityOf(modulePreview));
    sleepSec(10);//что-то с админкой(не прогружает превью модуля)
    createButton.click();
    waitLoading();

    log.info("moduleNameString->" + moduleNameString);
    log.info("curseCreatedName->" + curseCreatedName.getText());

    return moduleNameString.equals(curseCreatedName.getText());

    }
}
