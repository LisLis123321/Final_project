package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class EditPage {

    private final AddNewAdPage addNewAdPage = new AddNewAdPage();

    private final SelenideElement editButton = $$(".buttonSecondary")
            .findBy(Condition.text("Редактировать объявление"));
    private final SelenideElement saveButton = $x("//button[text()='Сохранить изменения']");
    private final SelenideElement newAdCard = $x("//div[@class='card']/div");
    private final SelenideElement deleteAdCardButton = $x("//button[contains(text(),'Удалить')]");
    private final SelenideElement textOnProfilePage = $x("//h1[text()='Избранные объявления']");


    public void placeNewAdForEdit(String category, String city, String price, String name, String description) {

        addNewAdPage.selectCategory(category);
        addNewAdPage.selectCity(city);
        addNewAdPage.getNameAd().shouldBe(visible).setValue(name);
        addNewAdPage.getProductDescription().shouldBe(visible).setValue(description);
        addNewAdPage.getProductPrice().shouldBe(visible).setValue(price);
        addNewAdPage.getToPublishButton().shouldBe(visible).click();
    }


    public boolean verifyAdExists(String adName) {
        // Ищем любой h2, который СОДЕРЖИТ наш uniqueName, и ждем до 5 секунд
        $$x("//*[contains(text(), '" + adName + "')]").first()
                .shouldBe(visible, Duration.ofSeconds(5));
        return true;
    }


    public SelenideElement getEditButton() {
        return editButton;
    }

    public AddNewAdPage getAddNewAdPage() {
        return addNewAdPage;
    }

    public SelenideElement getSaveButton() {
        return saveButton;
    }

    public SelenideElement getDeleteAdCard() {
        return deleteAdCardButton;
    }

    public SelenideElement getTextOnProfilePage() {
        return textOnProfilePage;
    }

    public SelenideElement getNewAdCard() {
        return newAdCard;
    }
}
