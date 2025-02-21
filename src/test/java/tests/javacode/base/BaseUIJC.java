package tests.javacode.base;

import lombok.extern.slf4j.Slf4j;
import org.ex.config.WaitingConfig;
import org.ex.pages.base.BasePage;
import org.ex.pages.base.BeforeLogin;
import org.ex.pages.base.Root;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@Slf4j
public class BaseUIJC {
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    protected static WebDriver getWebDriver() {
        return webDriverThreadLocal.get();
    }

    protected static BasePage basePage;
    protected static Root root;

    @BeforeEach  // Изменено на BeforeEach
    @DisplayName("Авторизация на портале")
    public void setup() {
        WebDriver webDriver = new FirefoxDriver();
        webDriverThreadLocal.set(webDriver); // Сохраняем WebDriver в ThreadLocal

        webDriver.manage().timeouts()
                .implicitlyWait(WaitingConfig.IMPLICIT_WAIT.getDuration());
        webDriver.manage().timeouts()
                .pageLoadTimeout(WaitingConfig.PAGE_LOAD_TIMEOUT.getDuration());
        webDriver.manage().timeouts()
                .scriptTimeout(WaitingConfig.SCRIPT_TIMEOUT.getDuration());

        webDriver.manage().window().maximize();
        before();
    }

    private void before() { //Убрали static
        WebDriver webDriver = getWebDriver(); // Получаем WebDriver из ThreadLocal
        basePage = new BasePage(webDriver);
        root = new Root(webDriver);
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

    @AfterEach   // Изменено на AfterEach
    public void tearDown() {
        WebDriver webDriver = webDriverThreadLocal.get();
        if (webDriver != null) {
            webDriver.quit();
            log.info("Закрытие ресурсов выполнено _ @AfterAll");
        }
        webDriverThreadLocal.remove(); // Очищаем ThreadLocal
    }
}
