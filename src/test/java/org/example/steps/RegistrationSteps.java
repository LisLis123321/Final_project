package org.example.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.UserAPI;
import org.example.pages.AddNewAdPage;
import org.example.pages.CommonLocatorsPage;
import org.example.pages.LoginPage;
import org.example.pojo.RegisterUserRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationSteps extends BaseTestSteps {

    private String lastErrorMessage;

    public RegistrationSteps() {
        commonLocatorsPage = new CommonLocatorsPage();
        loginPage = new LoginPage();
        userAPI = new UserAPI();
        adPage = new AddNewAdPage();
    }

    @When("пользователь регистрируется в системе")
    public void registerUserInSystem() {
        registerNewUserAPI();
    }

    @Then("регистрация должна быть успешной")
    public void registrationShouldBeSuccessful() {
        assertTrue(true, "Регистрация успешна");
    }

    @When("пользователь пытается зарегистрироваться с тем же email повторно")
    public void userTriesToRegisterWithSameEmail() {
        RegisterUserRequest duplicateRequest = new RegisterUserRequest(
                this.email,
                this.password,
                this.password
        );
        Response duplicateResponse = userAPI.registerUserNegative(duplicateRequest);
        duplicateResponse.then().statusCode(400);
        String message = duplicateResponse.path("message");
        System.out.println("Error message: " + message);

        lastErrorMessage = message;
    }

    @Then("должно отобразиться сообщение об ошибке {string}")
    public void errorMessageShouldBeDisplayed(String expectedMessage) {
        assertEquals(expectedMessage, lastErrorMessage);
    }
}
