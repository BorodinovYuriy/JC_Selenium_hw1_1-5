package org.ex.pages.base;

import lombok.extern.slf4j.Slf4j;
import org.ex.config.PropertiesLoader;
import org.ex.config.WaitingConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Slf4j
public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait waitIt;

    protected String baseUrl;
    private final By loading;


    private final By addButton;
    private final By createButton;

    private final By rows;


    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        this.baseUrl = PropertiesLoader.getBaseURI();

        this.waitIt = new WebDriverWait(
                webDriver,
                WaitingConfig.WAITING_TIMEOUT.getDuration());

        this.loading =  By.xpath("//*[contains(text(), 'Loading')]");
        this.rows =  By.xpath("//div[@class = 'table-responsive']//tbody/tr");
        this.createButton = By.xpath("//div[@class = 'modalContent afade']//button[text() = 'Создать']");
        this.addButton = By.xpath("//button[@type = 'button' and text() = '+ Добавить']");
    }

    public void openPage(){
        webDriver.get(baseUrl);
    }
    public void waitLoading(){
        waitIt.until(ExpectedConditions.invisibilityOfElementLocated(loading));
    }
    public void clickOnAddButton(){
        waitIt.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }
    public void clickOnCreateButton(){
        waitIt.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }
    public boolean editQuiz(List<WebElement> list, String wordContains) {
        return list.stream()
                .anyMatch(
                        webElement -> webElement
                                .getText().contains(wordContains));
    }
    public List<WebElement> getRows(){
        waitLoading();
        return webDriver.findElements(rows);
    }
    public void sleepSec(int sec){
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            log.error("Остановка потока не удалась: {}", e.getMessage());
        }
    }

}
