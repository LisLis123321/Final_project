package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.AddNewAdPage;
import org.example.pages.CommonLocatorsPage;
import org.example.pages.LoginPage;
import org.example.api.UserAPI;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps extends BaseTestSteps {

    public LoginSteps() {
        commonLocatorsPage = new CommonLocatorsPage();
        loginPage = new LoginPage();
        userAPI = new UserAPI();
        adPage = new AddNewAdPage();
    }


    @Given("пользователь зарегистрирован в системе")
    public void userIsRegisteredInSystem() {
        registerNewUserAPI();
    }

    @When("пользователь открывает страницу входа")
    public void userOpensLoginPage() {
        commonLocatorsPage.clickOnEnterAndRegistrationButton();
    }

    @And("пользователь вводит email и пароль")
    public void userEntersEmailAndPassword() {
        loginPage.fillLoginFields(email, password);
    }

    @And("пользователь нажимает кнопку входа")
    public void userClicksLoginButton() {
        loginPage.clickOnEnterLoginButton();
    }

    @Then("пользователь должен быть успешно авторизован")
    public void userShouldBeLoggedInSuccessfully() {
        assertTrue(commonLocatorsPage.getLogOutButton().isEnabled());
    }
}
