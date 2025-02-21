package org.ex.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.Semaphore;

public class WebDriverPool {
    private WebDriverPool() {
    }

    private static final int MAX_DRIVERS = 2;
    private static final Semaphore semaphore = new Semaphore(MAX_DRIVERS, true);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() throws InterruptedException {
        semaphore.acquire();
        WebDriver driver = new FirefoxDriver();
        driverThreadLocal.set(driver);
        return driver;
    }

    public static void releaseDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            semaphore.release();
        }
    }
}
