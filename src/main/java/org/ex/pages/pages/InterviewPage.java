package org.ex.pages.pages;

import org.ex.pages.base.BasePage;
import org.ex.utils.creators.interviewcreator.InterviewNameCreator;
import org.ex.utils.creators.forall.DateCreator;
import org.ex.utils.creators.interviewcreator.InterviewGradeLinkCreator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class InterviewPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'modalContent afade']//input[@type = 'input']")
    private WebElement createInterviewInput;

    @FindBy(xpath = "//tbody[1]/tr/td[1]/span")
    private WebElement nameField;

    @FindBy(xpath = "//div[@class = 'mainCont']")
    private WebElement mainCont;

    @FindBy(xpath = "//input[@placeholder='Название']")
    private WebElement name;

    @FindBy(xpath = "//div[@class='react-datepicker__input-container']/input")
    private WebElement date;

    @FindBy(xpath = "//small[text() = 'Тип']/parent::div//select")
    private WebElement type;

//    @FindBy(xpath = "//small[text() = 'Тип']/parent::div//select/option[@value = 'HR']")
//    private WebElement hr;
//
//    @FindBy(xpath = "//small[text() = 'Тип']/parent::div//select/option[@value = 'tech']")
//    private WebElement tech;

    @FindBy(xpath = "//textarea[@placeholder = 'Собственная оценка интервью']")
    private WebElement grade;

    @FindBy(xpath = "//input[@placeholder = 'Видео ссылка']")
    private WebElement link;

    @FindBy(xpath = "//div[@class = 'pull-right']//button[text()='Сохранить']")
    private WebElement saveButton;

    @FindBy(xpath = "//a[@class = 'back-link pull-left']")
    private WebElement backLink;

    public InterviewPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public InterviewPage addNewClick() {
        clickOnAddButton();
        return this;
    }
    public InterviewPage createNewInterview(String interviewName) {
        waitIt.until(ExpectedConditions
                .visibilityOf(createInterviewInput)).sendKeys(interviewName);
        clickOnCreateButton();
        return this;
    }

    public void edit(String name, String date, String type, String grade, String link) {
        nameField.click();
        waitIt.until(ExpectedConditions.visibilityOf(mainCont));

        if(!name.equals("null")) {
            if(name.equals("пустое поле")){
                this.name.clear();
            }else{
                this.name.clear();
                this.name.sendKeys(InterviewNameCreator.createInterviewName(name));
            }
        }

        String setData = DateCreator.createDate(date);
        if(setData != null) this.date.sendKeys(setData);


        if (!type.equals("пустое поле")) {
            this.type.click();
            Select select = new Select(this.type);
            if (type.trim().equals("hr")) {
                select.selectByVisibleText("HR");
            } else if (type.trim().equals("tech")) {
                select.selectByVisibleText("tech");
            }
        }

        String setGrage = InterviewGradeLinkCreator.createGrade(grade);
        if (setGrage != null) this.grade.sendKeys(setGrage);

        String setLink = InterviewGradeLinkCreator.createGrade(link);
        if (setLink != null) this.link.sendKeys(setLink);

        saveButton.click();
        backLink.click();
        waitLoading();

    }
}
