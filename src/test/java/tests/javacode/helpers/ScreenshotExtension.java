package tests.javacode.helpers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import tests.javacode.base.BaseUIJC;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotExtension implements TestWatcher {



    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = BaseUIJC.getDriverToScrenShoot();
        takeScreenshot(driver, context.getRequiredTestMethod().getName());
    }

    private void takeScreenshot(WebDriver driver, String testName) {
        if (driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = String.format("screenshot_%s_%s.png", testName, timestamp);

            Path destination = Paths.get("screenshots", fileName);
            try {
                Files.createDirectories(destination.getParent());
                Files.copy(screenshot.toPath(), destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
