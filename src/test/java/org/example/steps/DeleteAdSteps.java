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
import org.example.pojo.AdResponse;
import java.time.Duration;
import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Selenide.$$x;

public class DeleteAdSteps extends BaseTestSteps {

    private int currentAdId;
    private String currentAdName;

    AddNewAdPage adPage;
    CommonLocatorsPage commonLocatorsPage;
    EditPage editPage;
    UserAPI userAPI;

    public DeleteAdSteps() {
        adPage = new AddNewAdPage();
        commonLocatorsPage = new CommonLocatorsPage();
        editPage = new EditPage();
        userAPI = new UserAPI();
    }

    @Given("пользователь успешно зарегистрировался и авторизовался в системе")
    public void loginUserInSystem() {
        loginAndGetToken();
    }

    @When("пользователь создает объявление с категорией {string}, городом {string}, названием {string}, описанием {string} и ценой {string}")
    public void createAdWithAPI(String category, String city, String name, String description, String price) {
        AdResponse ad = userAPI.createNewAd(this.token, name, category, "Новый", city, description, price);

        this.currentAdId = ad.getId();
        this.currentAdName = ad.getName();
    }

    @And("переходит в свой профиль")
    public void checkEditNewAd() {
        Selenide.refresh();
        commonLocatorsPage.getProfileButton().shouldBe(Condition.visible).click();
        editPage.getNewAdCard().shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
    }

    @And("удаляет это объявление")
    public void deleteAd() {
        userAPI.deleteAd(this.token, currentAdId);
    }

    @Then("объявление исчезает из профиля")
    public void verifyAdIsGoneFromProfile() {
        Selenide.refresh();
        commonLocatorsPage.getProfileButton().
                shouldBe(Condition.enabled)
                .click();
        $$x("//*[contains(text(), '" + currentAdName + "')]").shouldBe(empty);
    }
}
