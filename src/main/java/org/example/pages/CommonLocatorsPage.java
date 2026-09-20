package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;



public class CommonLocatorsPage {

    private LoginPage loginPage;

    private final SelenideElement logOutButton = $x(".//button[text()='Выйти']");

    private final SelenideElement profileButton = $x("//button[@class='circleSmall']");

    private final SelenideElement enterAndRegistrationButton = $x(".//button[text()='Вход и регистрация']");



    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public SelenideElement getLogOutButton() {
        return logOutButton;
    }

    public SelenideElement getProfileButton() {
        return profileButton;
    }


    public void clickOnEnterAndRegistrationButton() {
        enterAndRegistrationButton.click();
        getLoginPage().getEmailLoginField().shouldBe(Condition.visible);
    }
}


