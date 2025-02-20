package org.ex.pages.pages;

import org.ex.pages.base.BasePage;
import org.ex.utils.creators.forall.DateCreator;
import org.ex.utils.creators.usercreators.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class UserPage extends BasePage {

    /**
     * Имя,Фамилия,email,username,plain_password,roles,isCV,Открытие поиска,Статус поиска
     */

    @FindBy(xpath = "//small[text()='Имя']/following-sibling::input")
    private WebElement name;

    @FindBy(xpath = "//small[text()='Фамилия']/following-sibling::input")
    private WebElement surname;

    @FindBy(xpath = "//small[text()='Эл. почта']/following-sibling::input")
    private WebElement email;

    @FindBy(xpath = "//small[text()='Логин']/following-sibling::input")
    private WebElement login;

    @FindBy(xpath = "//small[text()='Пароль']/following-sibling::input")
    private WebElement password;

    @FindBy(xpath = "//small[text()='Роли']/following-sibling::input")
    private WebElement role;

    @FindBy(xpath = "//input[@class = 'form-control ib' and @type = 'checkbox']")
    private WebElement isCV;

    @FindBy(xpath = "//div[@class = 'react-datepicker-wrapper']//input")
    private WebElement searchOpen;

    @FindBy(xpath = "//div[@class='ib'][.//small[text()='Статус поиска']]//button[2]")
    private WebElement searchStatusButtonOnSearch;

    @FindBy(xpath = "//div[@class='ib'][.//small[text()='Статус поиска']]//button[3]")
    private WebElement searchStatusOnPause;

    @FindBy(xpath = "//div[@class='ib'][.//small[text()='Статус поиска']]//button[4]")
    private WebElement searchStatusOnProject;


    public UserPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public UserPage addNewClick() {
        clickOnAddButton();
        return this;
    }

    public void createUser(
            String name,
            String surname,
            String email,
            String login,
            String password,
            String roles,
            String isCV,
            String searchOpen,
            String searchStatus
    ) {

        String setName = NameSurnameCreator.createNameSurname(name);
        if (setName != null) this.name.sendKeys(setName);

        String setSurname = NameSurnameCreator.createNameSurname(surname);
        if (setSurname != null) this.surname.sendKeys(setSurname);

        String setEmail = EmailCreator.createEmail(email);
        if (setEmail != null) this.email.sendKeys(setEmail);

        String setLogin = LoginCreator.createLogin(login);
        if (setLogin != null) this.login.sendKeys(setLogin);

        String setPassword = PasswordCreator.createPassword(password);
        if (setPassword != null) this.password.sendKeys(setPassword);

        if (!roles.equals("-")) this.role.sendKeys(roles);

        if (!isCV.equals("-")) {
            this.isCV.click();
            if (isCV.equals("false")) this.isCV.click();
        }

        if (!searchStatus.equals("-")) {
            if (searchStatus.equals("active_search")) this.searchStatusButtonOnSearch.click();
            if (searchStatus.equals("pause_search")) this.searchStatusOnPause.click();
            if (searchStatus.equals("on_project")) this.searchStatusOnProject.click();
        }

        String setSearchOpen = DateCreator.createDate(searchOpen);
        if (setSearchOpen != null) this.searchOpen.sendKeys(setSearchOpen);

        clickOnCreateButton();
    }
}
