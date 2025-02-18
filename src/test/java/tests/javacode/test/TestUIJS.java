package tests.javacode.test;

import lombok.extern.slf4j.Slf4j;
import org.ex.pages.pages.InterviewPage;
import org.ex.pages.pages.QuestionPage;
import org.ex.utills.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

        basePage.waitLoading();

        root.clickOnInterviewLink();

        List<WebElement> createdNewInterview = interview
                .addNewClick()
                .createNewInterview(randomWords)
                .getRows();

        Assertions.assertTrue(
                interview.checkCreate(createdNewInterview,randomWords),
                "Созданного интервью не найдено в списке интервью на странице!");

        log.info("Добавление нового интервью - passed");
    }
    @Test
    @DisplayName("Добавление нового вопроса")
    void addNewQuestion() {
        QuestionPage question = new QuestionPage(webDriver);
        String randomWords = TestData.makeWords();

        basePage.waitLoading();

        root.clickOnQuestionLink();

        List<WebElement> createdNewQuestion = question
                .addNewClick()
                .createQuestion(randomWords)
                .getRows();

        Assertions.assertTrue(
                question.checkCreate(createdNewQuestion,randomWords),
                "Созданного интервью не найдено в списке интервью на странице!");

        log.info("Добавление нового вопроса - passed");
    }


}
