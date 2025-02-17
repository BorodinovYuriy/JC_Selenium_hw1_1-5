package org.ex.pages.base;

import lombok.extern.slf4j.Slf4j;
import org.ex.config.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class BasePage {
    protected WebDriver webDriver;
    protected String baseUrl;

    private final By loading;

    protected WebDriverWait waitIt;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        this.baseUrl = PropertiesLoader.getBaseURI();
        this.waitIt = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        this.loading =  By.xpath("//*[contains(text(), 'Loading')]");
    }

    public void openPage(){
        webDriver.get(baseUrl);
    }

    public void sleepSec(int sec){
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            log.error("Остановка потока не удалась: {}", e.getMessage());
        }
    }
    public void waitLoading(){
        waitIt.until(ExpectedConditions.invisibilityOfElementLocated(loading));
    }
}
