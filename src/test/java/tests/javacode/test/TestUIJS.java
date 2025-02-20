package tests.javacode.test;

import lombok.extern.slf4j.Slf4j;
import org.ex.config.TimingExtension;
import org.ex.pages.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;
import tests.javacode.base.BaseUIJC;

import java.util.List;

import static org.ex.utils.base.StaticBaseUtils.FAKER;

@ExtendWith(TimingExtension.class)
@Slf4j
class TestUIJS extends BaseUIJC {

    @Test
    @DisplayName("Добавление нового интервью")
    void addNewInterview() {
        InterviewPage interview = new InterviewPage(webDriver);
        String randomWords = FAKER.lorem().sentence(5);

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
        String randomWords = FAKER.lorem().sentence(5);

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
        String randomWords = FAKER.lorem().sentence(5);

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
    //todo доделать согласно требованиям из экселя!
    @Test
    @DisplayName("Добавление нового модуля")
    void addNewModule() {
        ModulePage module = new ModulePage(webDriver);
        String randomWords = FAKER.lorem().sentence(5);

        basePage.waitLoading();

        root.clickOnModuleLink();

        List<WebElement> createdNewQuiz = module
                .addNewClick()
                .createModule(randomWords)
                .getRows();

        Assertions.assertTrue(
                module.checkCreate(createdNewQuiz,randomWords),
                "Созданного модуля не найдено в списке модулей на странице!");

        log.info("Добавление нового модуля - passed");
    }
    @Test
    @DisplayName("Создание нового курса")
    void addNewCurse(){


    }

    @ParameterizedTest
    @DisplayName("Создание нового пользователя")
    @CsvFileSource(resources = "testDataUsers.csv")
    void addNewUser(
            String name,
            String surname,
            String email,
            String login,
            String password,
            String roles,
            String isCV,
            String searchOpen,
            String searchStatus){

        UserPage user = new UserPage(webDriver);

        basePage.waitLoading();
        root.clickOnUserLink();

        user.addNewClick()
        .createUser(name,surname,email,login,password,roles,isCV, searchOpen,searchStatus);

    }
    @Test
    @DisplayName("Создание нового экзамена")
    void addNewExam(){

    }
    @Test
    @DisplayName("Создание голосовой записи")
    void addNewVoiceRecording(){

    }
    //todo воспользоваться Гуглдоком!!! и Параметризовать
    @ParameterizedTest
    @DisplayName("Редактирование интервью")
    @CsvFileSource(resources = "testDataInterview.csv")
    //название, дата, тип, оценка, ссылка
    void editInterview(String name, String date, String type, String grade, String link) {

        InterviewPage interview = new InterviewPage(webDriver);

        basePage.waitLoading();
        root.clickOnInterviewLink();
        basePage.waitLoading();

        interview.edit(name,date,type,grade,link);

    }

}
