package org.example.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.AddNewAdPage;
import org.example.pages.CommonLocatorsPage;
import org.example.pages.EditPage;
import org.junit.jupiter.api.Assertions;


public class AddAdSteps extends BaseTestSteps {

    private String nameAd;

    public AddAdSteps() {
        adPage = new AddNewAdPage();
        commonLocatorsPage = new CommonLocatorsPage();
        editPage = new EditPage();
    }

    @Given("пользователь зарегистрировался и авторизовался в системе")
    public void loginUser() {
        loginAndGetToken();
    }

    @When("пользователь создает объявление с категорией {string}, названием {string}, описанием {string} и ценой {string}")
    public void createAd(String category, String name, String description, String price) {
        String uniqueName = name + " " + System.currentTimeMillis();

        adPage.placeNewAdForParametrizedTest(category, uniqueName, description, price);
        nameAd = uniqueName;
    }

    @And("пользователь переходит в свой профиль")
    public void goToUserProfile() {
        Selenide.refresh();
        commonLocatorsPage.getProfileButton().
                shouldBe(Condition.enabled)
                .click();

    }

    @Then("созданное объявление отображается на странице профиля")
    public void checkNewCreatedAd() {
        Assertions.assertTrue(editPage.verifyAdExists(nameAd));
    }
}
