package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private CommonLocatorsPage commonLocatorsPage;



    private final SelenideElement emailLoginField = $x(".//input[@placeholder='Введите Email']");

    private final SelenideElement passwordLoginField = $x(".//input[@placeholder='Пароль']");

    private final SelenideElement enterLoginButton = $x(".//button[text()='Войти']");


    public CommonLocatorsPage getCommonLocatorsPage() {
        if (commonLocatorsPage == null) {
            commonLocatorsPage = new CommonLocatorsPage();
        }
        return commonLocatorsPage;
    }

    public SelenideElement getEmailLoginField() {
        return emailLoginField;
    }

    public void fillLoginFields(String email, String password) {
        emailLoginField.setValue(email);
        passwordLoginField.setValue(password);
    }

    public void clickOnEnterLoginButton() {
        enterLoginButton.click();
        getCommonLocatorsPage().getLogOutButton().shouldBe(Condition.visible);
    }
}
