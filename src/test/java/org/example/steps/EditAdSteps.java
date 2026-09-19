package org.example.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.api.UserAPI;
import org.example.pages.AddNewAdPage;
import org.example.pages.CommonLocatorsPage;
import org.example.pages.EditPage;
import org.junit.jupiter.api.Assertions;
import java.time.Duration;

public class EditAdSteps extends BaseTestSteps {

    private String updatedName;

    AddNewAdPage adPage;
    CommonLocatorsPage commonLocatorsPage;
    EditPage editPage;
    UserAPI userAPI;

    public EditAdSteps() {
        adPage = new AddNewAdPage();
        commonLocatorsPage = new CommonLocatorsPage();
        editPage = new EditPage();
        userAPI = new UserAPI();
    }

    @Given("пользователь выполнил регистрацию и успешно авторизовался в системе")
    public void checkCreatingAnAd() {
        loginAndGetToken();
    }

    @When("пользователь создает объявление с категорией {string}, городом {string}, названием {string}, описанием {string}, ценой {string}")
    public void createAdWithAPI(String category, String city, String name, String description, String price) {
        userAPI.createNewAd(this.token, name, category, "Новый", city, description, price);
    }

    @And("юзер переходит в свой профиль")
    public void checkEditNewAd() {
        Selenide.refresh();
        commonLocatorsPage.getProfileButton().shouldBe(Condition.visible).click();
        editPage.getNewAdCard().shouldBe(Condition.visible,Duration.ofSeconds(3)).click();
    }

    @And("пользователь нажимает на кнопку редактирования своего объявления")
    public void clickOnEditButton() {
        editPage.getEditButton().shouldBe(Condition.visible, Duration.ofSeconds(2)).click();

    }

    @And("редактирует объявление с категорией {string}, городом {string}, названием {string}, описанием {string}, ценой {string} и сохраняет его")
    public void placeNewAdForEdit(String updatedCategory, String updatedCity, String updatedName, String updatedDescription, String updatedPrice) {
        this.updatedName = updatedName;
        editPage.placeNewAdForEdit(updatedCategory, updatedCity, updatedPrice, updatedName, updatedDescription);
    }

    @Then("созданное объявление отображается в профиле")
    public void checkNewAd() {
        Selenide.refresh();
        commonLocatorsPage.getProfileButton().
                shouldBe(Condition.enabled)
                .click();
        Assertions.assertTrue(editPage.verifyAdExists(updatedName));
    }
}
