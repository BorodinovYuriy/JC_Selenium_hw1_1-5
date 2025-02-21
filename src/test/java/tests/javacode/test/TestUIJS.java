package tests.javacode.test;

import lombok.extern.slf4j.Slf4j;
import org.ex.config.TimingExtension;
import org.ex.pages.base.BeforeLogin;
import org.ex.pages.pages.*;
import org.ex.pages.pages.razvitie.itk.academy.RecordingPages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
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

    @Test
    @DisplayName("Создание нового курса")
    //Написано норм, но пока на проде -504я
    // - тест не может дождаться элемента и кликает "Создать" впустую....
    void addNewCurse(){
        ModulePage module = new ModulePage(webDriver);
        CursePage curse = new CursePage(webDriver);

        basePage.waitLoading();
        root.clickOnModuleLink();
        String mouleId = module.getId();

        root.clickOnCurseLink();
        basePage.waitLoading();

        Assertions.assertTrue(curse.perform(mouleId),
                "Создаваемый курс не найден в списке курсов!");
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
    @Test
    @DisplayName("Создание голосовой записи")
    void addNewVoiceRecording(){
        webDriver.switchTo().newWindow(WindowType.TAB);
/*
Так как я на FireFox - на сайте при клике-запись - баг! (продолжить не могу)
это просто пример на старом UI - проде...

...так - потренить переход по окнам \(0_0)/
 */
        webDriver.get("https://razvitie.itk.academy/main");

        BeforeLogin before = new BeforeLogin(webDriver);
        RecordingPages rec = new RecordingPages(webDriver);

        before.sleepSec(1);
        before.login();
        before.sleepSec(1);
        rec.checkRecording();

        webDriver.close();

        String originalWindow = webDriver.getWindowHandles().iterator().next();
        webDriver.switchTo().window(originalWindow);
    }
    @Test
    @DisplayName("Добавление нового модуля с вопросом")
    void addNewModuleAddQuestion() {
        QuestionPage question = new QuestionPage(webDriver);
        ModulePage module = new ModulePage(webDriver);
        String randomWords = FAKER.lorem().sentence(5);

        basePage.waitLoading();
        root.clickOnQuestionLink();
        String questionId = question.getQuestionFirstId();

        root.clickOnModuleLink();

        List<WebElement> createdNewQuiz = module
                .addNewClick()
                .createModule(randomWords,questionId)
                .getRows();

        Assertions.assertTrue(
                module.checkCreate(createdNewQuiz,randomWords),
                "Созданного модуля не найдено в списке модулей на странице!");

        log.info("Добавление нового модуля с вопросом - passed");
    }

    @Test
    @DisplayName("Создание нового экзамена")
    void addNewExam(){
        ExamPage exam = new ExamPage(webDriver);
        QuestionPage question = new QuestionPage(webDriver);

        basePage.waitLoading();
        root.clickOnQuestionLink();
        basePage.waitLoading();
        String questionId = question.getQuestionFirstId();
        root.clickOnExamLink();

        Assertions.assertTrue(exam.addNewAndCheck(questionId),
                "Название отредактированного экзамена и экзамена в общем списке - не совпадают!");
    }











    @Test
    @DisplayName("Редактирование экзамена (по нажатию на id)")
    void editExam(){
        ExamPage exam = new ExamPage(webDriver);
        basePage.waitLoading();
        root.clickOnExamLink();

        Assertions.assertTrue(exam.editCheck(),
                "Название отредактированного экзамена и экзамена в общем списке - не совпадают!");

    }

    @Test
    @DisplayName("Добавление нового модуля (без_вопроса)")
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
}
