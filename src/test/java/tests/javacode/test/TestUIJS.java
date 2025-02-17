package tests.javacode.test;

import lombok.extern.slf4j.Slf4j;
import org.ex.pages.pages.InterviewPage;
import org.ex.utills.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import tests.javacode.base.BaseUIJC;

import java.util.List;

@Slf4j
class TestUIJS extends BaseUIJC {

    @Test
    @DisplayName("Добавление нового интервью")
    void addNewInterview() {
        InterviewPage interview = new InterviewPage(webDriver);
        String randomWords = TestData.makeWords();

        afterLogin.clickOnInterviewLink();
        List<WebElement> createdNewInterview = interview
                .addNewClick()
                .createNewInterview(randomWords)
                .getRows();

        Assertions.assertTrue(
                afterLogin.checkCreate(createdNewInterview,randomWords),
                "Созданного интервью не найдено в списке всех интервью!");

        log.info("Добавление нового интервью - passed");
    }


}
