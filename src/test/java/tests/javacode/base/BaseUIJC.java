package tests.javacode.base;

import lombok.extern.slf4j.Slf4j;
import org.ex.config.PropertiesLoader;
import org.ex.config.WaitingConfig;
import org.ex.pages.base.BasePage;
import org.ex.pages.base.BeforeLogin;
import org.ex.pages.base.Root;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
@Slf4j
public class BaseUIJC {
    protected static WebDriver webDriver;
    protected static BasePage basePage;
    protected static Root root;

    @BeforeAll
    @DisplayName("Авторизация на портале")
    public static void setup() {

        webDriver = new FirefoxDriver();

        webDriver.manage().timeouts()
                .implicitlyWait(WaitingConfig.IMPLICIT_WAIT.getDuration());
        webDriver.manage().timeouts()
                .pageLoadTimeout(WaitingConfig.PAGE_LOAD_TIMEOUT.getDuration());
        webDriver.manage().timeouts()
                .scriptTimeout(WaitingConfig.SCRIPT_TIMEOUT.getDuration());

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

        WebElement profile = webDriver.findElement(By.xpath("//div[@class = 'menuProfile']"));
        Assertions.assertNotNull(profile,"Профиль администратора не загрузился!");
        log.info("Авторизация на портале - passed");
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
            log.info("Закрытие ресурсов выполнено _ @AfterAll");
        }
    }
}
