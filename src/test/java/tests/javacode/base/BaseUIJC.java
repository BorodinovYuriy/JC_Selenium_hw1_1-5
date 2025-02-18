package tests.javacode.base;

import lombok.extern.slf4j.Slf4j;
import org.ex.config.PropertiesLoader;
import org.ex.pages.base.BasePage;
import org.ex.pages.base.Root;
import org.ex.pages.base.BeforeLogin;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
@Slf4j
public class BaseUIJC {
    protected static WebDriver webDriver;
    protected static BasePage basePage;
    protected static Root root;

    @BeforeAll
    @DisplayName("Авторизация на портале")
    public static void setup() {

        webDriver = new FirefoxDriver();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        webDriver.manage().window().maximize();
        before();
    }

    private static void before() {
        basePage = new BasePage(webDriver);
        root = new Root(webDriver);
        basePage.openPage();
        log.info("Вход: {}", webDriver.getTitle());
        Assertions.assertNotNull(
                webDriver.getTitle(),
                "Не удалось загрузить стартовую страницу");

        BeforeLogin before = new BeforeLogin(webDriver);
        before.getLoginField().sendKeys(PropertiesLoader.getUsername());
        before.getPasswordField().sendKeys(PropertiesLoader.getPassword());
        before.getLoginButton().click();
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
            log.info("Закрытие ресурсов выполнено _ @AfterAll");
        }
    }
}
