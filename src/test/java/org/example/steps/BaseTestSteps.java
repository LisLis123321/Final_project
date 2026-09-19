package org.example.steps;

import com.github.javafaker.Faker;
import java.util.Locale;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.api.UserAPI;
import org.example.pages.AddNewAdPage;
import org.example.pages.CommonLocatorsPage;
import org.example.pages.EditPage;
import org.example.pages.LoginPage;
import org.example.pojo.CreateNewAdRequest;
import org.example.pojo.LoginUserRequest;
import org.example.pojo.RegisterUserRequest;

import java.util.UUID;

public class BaseTestSteps {

    protected UserAPI userAPI;
    protected CommonLocatorsPage commonLocatorsPage;
    protected LoginPage loginPage;
    protected AddNewAdPage adPage;
    protected EditPage editPage;

    protected static final String BASE_URL = "https://qa-desk.education-services.ru";

    protected String email;
    protected String password;
    protected String token;


    public BaseTestSteps() {
        RestAssured.baseURI = BASE_URL;
        userAPI = new UserAPI();
        commonLocatorsPage = new CommonLocatorsPage();
        loginPage = new LoginPage();
        adPage = new AddNewAdPage();
    }

    protected void registerNewUserAPI() {
        email = "autotest-" + UUID.randomUUID().toString().substring(0, 8) + "@yandex.ru";
        password = "password123";

        RegisterUserRequest registerRequest = new RegisterUserRequest(email, password, password);
        Response response = userAPI.registerUser(registerRequest);
        response.then().statusCode(201);
        this.token = normalizeToken(response.path("accessToken"));
    }

    protected String normalizeToken(String token) {
        if (token == null) return null;
        token = token.replace("\"", "").trim();
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return token;
    }

    protected void loginAndGetToken() {
        registerNewUserAPI();
        LoginUserRequest userRequest = new LoginUserRequest(this.email, this.password);
        Response loginResponse = userAPI.loginUserAndGetToken(userRequest);
        String rawToken = loginResponse.path("token.access_token");
        this.token = normalizeToken(rawToken);
        Selenide.executeJavaScript("localStorage.setItem('token', '" + this.token + "');");
        Selenide.executeJavaScript("localStorage.setItem('islogin', 'true');");
        Selenide.refresh();
    }
}