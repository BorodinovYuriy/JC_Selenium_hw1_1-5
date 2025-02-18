package tests.javacode.test;

import lombok.extern.slf4j.Slf4j;
import org.ex.pages.pages.InterviewPage;
import org.ex.pages.pages.QuestionPage;
import org.ex.pages.pages.QuizPage;
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
                "Созданного вопроса не найдено в списке вопросов на странице!");

        log.info("Добавление нового вопроса - passed");
    }
    @Test
    @DisplayName("Добавление нового квиза")
    void addNewQuiz() {
        QuizPage quiz = new QuizPage(webDriver);
        String randomWords = TestData.makeWords();

        basePage.waitLoading();

        root.clickOnQuizLink();

        List<WebElement> createdNewQuiz = quiz
                .addNewClick()
                .createQuiz(randomWords)
                .getRows();

        Assertions.assertTrue(
                quiz.checkCreate(createdNewQuiz,randomWords),
                "Созданного квиза не найдено в списке квизов на странице!");

        log.info("Добавление нового квиза - passed");
    }


}
