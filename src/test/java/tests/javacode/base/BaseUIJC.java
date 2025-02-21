package tests.javacode.base;

import lombok.extern.slf4j.Slf4j;
import org.ex.config.WaitingConfig;
import org.ex.config.WebDriverPool;
import org.ex.pages.base.BasePage;
import org.ex.pages.base.BeforeLogin;
import org.ex.pages.base.Root;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Slf4j
public class BaseUIJC {
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        return webDriverThreadLocal.get();
    }

    private static WebDriver toScreenShotWD;

    protected BasePage basePage;
    protected Root root;

    @BeforeEach
    @DisplayName("Авторизация на портале")
    public void setup() {
        try {
            // Получаем WebDriver из пула
            WebDriver webDriver = WebDriverPool.getDriver();
            // Сохраняем WebDriver в ThreadLocal
            webDriverThreadLocal.set(webDriver);

            toScreenShotWD = webDriver;

            webDriver.manage().timeouts()
                    .implicitlyWait(WaitingConfig.IMPLICIT_WAIT.getDuration());
            webDriver.manage().timeouts()
                    .pageLoadTimeout(WaitingConfig.PAGE_LOAD_TIMEOUT.getDuration());
            webDriver.manage().timeouts()
                    .scriptTimeout(WaitingConfig.SCRIPT_TIMEOUT.getDuration());

            webDriver.manage().window().maximize();
            basePage = new BasePage(webDriver);
            root = new Root(webDriver);
            before();
        } catch (InterruptedException e) {
            log.error("Ошибка при получении WebDriver из пула", e);
            Thread.currentThread().interrupt();
        }
    }

    private void before() {
        WebDriver webDriver = getWebDriver(); // Получаем WebDriver из ThreadLocal

        basePage.openPage();
        log.info("Вход: {}", webDriver.getTitle());
        Assertions.assertNotNull(
                webDriver.getTitle(),
                "Не удалось загрузить стартовую страницу");

        BeforeLogin before = new BeforeLogin(webDriver);
        before.login();

        WebElement profile = webDriver.findElement(By.xpath("//div[@class = 'menuProfile']"));
        Assertions.assertNotNull(profile, "Профиль администратора не загрузился!");
        log.info("Авторизация на портале - passed");
    }

    @AfterEach
    public void tearDown() {
        WebDriver webDriver = webDriverThreadLocal.get();
        if (webDriver != null) {
            webDriver.quit();
            log.info("Закрытие ресурсов выполнено _ @AfterAll");
            WebDriverPool.releaseDriver(); // Освобождаем WebDriver обратно в пул
        }
        webDriverThreadLocal.remove(); // Очищаем ThreadLocal
    }
    public static WebDriver getDriverToScrenShoot(){
        return toScreenShotWD;
    }
}