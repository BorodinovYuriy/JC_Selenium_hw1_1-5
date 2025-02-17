package tests.javacode.base;

import lombok.extern.slf4j.Slf4j;
import org.ex.config.PropertiesLoader;
import org.ex.pages.base.BasePage;
import org.ex.pages.pages.AfterLogin;
import org.ex.pages.pages.BeforeLogin;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
@Slf4j
public class BaseUIJC {
    protected static WebDriver webDriver;

    public BasePage basePage;
    protected AfterLogin afterLogin;


    @BeforeAll
    public static void setup() {

        webDriver = new FirefoxDriver();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        webDriver.manage().window().maximize();
    }

    @BeforeEach
    @DisplayName("Авторизация на портале")
    public void before() {
        basePage = new BasePage(webDriver);
        basePage.openPage();
        log.info("Вход: {}", webDriver.getTitle());
        Assertions.assertNotNull(
                webDriver.getTitle(),
                "Не удалось загрузить стартовую страницу");

        BeforeLogin before = new BeforeLogin(webDriver);
        before.getLoginField().sendKeys(PropertiesLoader.getUsername());
        before.getPasswordField().sendKeys(PropertiesLoader.getPassword());
        before.getLoginButton().click();

        afterLogin = new AfterLogin(webDriver);
        afterLogin.waitSetTable();
        Assertions.assertTrue(afterLogin.tableIsVisible());
        log.info("Вход admin выполнен _ @BeforeEach");
    }


    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
            log.info("Закрытие ресурсов выполнено _ @AfterAll");
        }
    }
}
