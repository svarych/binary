package com.tober.binary;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Tests {

    private static SelenideElement card;

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = false;
        open("https://volodymyr-kushnir.github.io/recipes/#/");
        getWebDriver().manage().window().maximize();

        // Ignoring 25% throwable
        SelenideElement retry = $(byXpath("//a[.='— Retry.']"));
        if (retry.isDisplayed()) {
            while (retry.isDisplayed()) {
                retry.click();
                retry.shouldBe(disappear);
            }
        }

        // Ensure, recipe cards is loaded
        card = $(byXpath("(//div[contains(@class,'ui fluid card')])[1]"));
        card.shouldBe(visible);
        //...
    }

    // PAGE
    // 1. Header should be loaded (one random header)
    @Test
    @DisplayName("Page has big header")
    void hasHeader() {
        $(byXpath("//div[contains(@class,'ui huge center aligned header')]")).shouldBe(visible);
    }

    // 1. Header should be loaded (one random header)
    @Test
    @DisplayName("Page has recipe block with 15 recipes")
    void hasRecipesBlock() {
        assertEquals(15, $$(byXpath("(//div[contains(@class,'ui fluid card')])")).size());
    }

    // RECIPES
    // 0.0 Recipe block header has "favorite"
    @Test
    @DisplayName("Recipe block header has 'favorite'")
    void hasFavorite() {
        card.find(byClassName("star")).shouldBe(visible);
    }

    //0.1 Recipe block header has "already cooked" checkboxes and timer
    @Test
    @DisplayName("Recipe block header has 'already cooked'")
    void hasCooked() {
        card.find(byClassName("spoon")).shouldBe(visible);
    }

    //0.2 Recipe block header has timer
    @Test
    @DisplayName("Recipe block header has 'timer'")
    void hasTimer() {
        card.find(byXpath("//span[contains(text(),'ago')]")).shouldBe(visible);
    }

    // 1. Recipe block has picture
    @Test
    @DisplayName("Recipe block header has 'picture;'")
    void hasPicture() {
        card.find(byXpath("//a/img")).shouldBe(visible);
    }

    // 2. Recipe block has header with dish name
    @Test
    @DisplayName("Recipe block has header with dish name")
    void hasDishName() {
        card.find(byXpath("//div[@class='content']/a")).shouldBe(visible);
    }

    // 3. Recipe block has meta data about ingridients used (hash-tags)
    @Test
    @DisplayName("Has meta about ingridients (hash-tags)")
    void hasIngridientsHash() {
        card.find(byXpath("//div[@class='meta']/div[@role='list']")).shouldBe(visible);
    }

    // 4. Recipe block has dish description
    @Test
    @DisplayName("Recipe block has dish description")
    void hasDishDescription() {
        card.find(byXpath("//div[@class='description']")).shouldBe(visible);
    }

    // 5. Recipe block footer has "Ingridients" block
    @Test
    @DisplayName("Recipe has has 'Ingridients' block")
    void hasIngridientsBlock() {
        card.find(byXpath("//div[contains(text(),'Ingredients')]")).shouldBe(visible);
    }

    // INTEGRATION
    // 6. "'Ingridients' block has true count of reagents"
    @Test
    @DisplayName("'Ingridients' block has true count of reagents")
    void hasListOfIngridients() {
        SelenideElement reagents = card.find(byXpath("(//div[contains(text(),'Ingredients')])"));
        int size = Integer.parseInt(reagents.find(byXpath("//small")).getText().replace("(", "").replace(")", ""));
        reagents.click();

        ElementsCollection reagentsList = card.find(byXpath("//div[contains(@class,'content active')]")).findAll(byAttribute("role", "listitem"));
        assertEquals(size, reagentsList.size());
    }
}
