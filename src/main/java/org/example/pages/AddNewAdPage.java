package org.example.pages;

import com.codeborne.selenide.*;
import java.util.Random;
import static com.codeborne.selenide.Selenide.*;

public class AddNewAdPage {

    private final SelenideElement placeAnAd = $x(".//button[text()='Разместить объявление']");

    public SelenideElement getNameAd() {
        return nameAd;
    }

    private final SelenideElement nameAd = $x(".//input[@placeholder='Название']");
    private final SelenideElement productDescription = $x(".//textarea[@placeholder='Описание товара']");
    private final SelenideElement productPrice = $x(".//input[@placeholder='Стоимость']");
    private final SelenideElement toPublishButton = $x(".//button[text()='Опубликовать']");
    private final SelenideElement accordionCitiesButton = $x("//input[@name='city']/following-sibling::button");
    private final ElementsCollection listOfCities = $$x("//div[contains(@class, 'dropDownMenu_options')]//button");
    private final SelenideElement accordionCategoriesButton = $x("//input[@name='category']/following-sibling::button");
    private final ElementsCollection listOfCategories = $$x("//div[contains(@class, 'dropDownMenu_options')]//button");


    public void selectRandomCity() {

        accordionCitiesButton.shouldBe(Condition.visible).click();
        listOfCities.shouldHave(CollectionCondition.sizeGreaterThan(0));
        int size = listOfCities.size();
        System.out.println("Всего городов в списке " + size);
        Random random = new Random();
        int randomIndex = random.nextInt(listOfCities.size());
        listOfCities.get(randomIndex)
                .shouldBe(Condition.visible)
                .click();

        System.out.println("Выбран город под индексом: " + randomIndex);
    }

    public void selectCategory(String categoryName) {
        String cleanName = categoryName.trim();
        SelenideElement dropdownButton = $("button.dropDownMenu_arrowDown__pfGL1, button[class*='arrowDown']");
        dropdownButton.shouldBe(Condition.visible).click();
        listOfCategories.shouldHave(CollectionCondition.sizeGreaterThan(0));
        SelenideElement targetCategory = listOfCategories.findBy(Condition.text(cleanName));
        targetCategory.shouldBe(Condition.visible).click();

        System.out.println("Выбрана категория под индексом: " + cleanName);
    }

    public void placeNewAdForParametrizedTest(String category, String name, String description, String price) {
        placeAnAd.shouldBe(Condition.visible).click();
        selectCategory(category);
        selectRandomCity();
        nameAd.shouldBe(Condition.visible).setValue(name);
        productDescription.shouldBe(Condition.visible).setValue(description);
        productPrice.shouldBe(Condition.visible).setValue(price);
        toPublishButton.shouldBe(Condition.visible).click();

    }

    public void selectCity(String cityName) {
        String cleanName = cityName.trim();
        accordionCitiesButton.shouldBe(Condition.visible).click();
        listOfCities.shouldHave(CollectionCondition.sizeGreaterThan(0));
        SelenideElement targetCity = listOfCities.findBy(Condition.text(cleanName));
        targetCity.shouldBe(Condition.visible).click();

        System.out.println("Выбрана категория под индексом: " + cleanName);
    }

    public SelenideElement getPlaceAnAd() {
        return placeAnAd;
    }

    public SelenideElement getProductPrice() {
        return productPrice;
    }

    public SelenideElement getAccordionCitiesButton() {
        return accordionCitiesButton;
    }

    public ElementsCollection getListOfCities() {
        return listOfCities;
    }

    public SelenideElement getAccordionCategoriesButton() {
        return accordionCategoriesButton;
    }

    public ElementsCollection getListOfCategories() {
        return listOfCategories;
    }

    public SelenideElement getProductDescription() {
        return productDescription;
    }

    public SelenideElement getToPublishButton() {
        return toPublishButton;
    }
}