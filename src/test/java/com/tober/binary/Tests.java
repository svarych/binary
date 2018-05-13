package com.tober.binary;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

class Tests extends MainPage {

    private static SelenideElement card;

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        open("https://volodymyr-kushnir.github.io/recipes/#/");
        getWebDriver().manage().window().maximize();

        // Ignoring 25% throwable
        SelenideElement retry = $(byXpath("//a[.='— Retry.']"));
        if (retry.isDisplayed()){
            while (retry.isDisplayed()){
                retry.click();
                retry.shouldBe(disappear);
            }
        }
        card = $(byXpath("(//div[contains(@class,'ui fluid card')])[1]"));
        //...
    }

    // 0. Recipe block header has "favorite" and "already cooked" checkboxes and timer
    @Test
    @DisplayName("Recipe block header has 'favorite'")
    void hasFavorite() {
        card.find(byClassName("star")).shouldBe(visible);
    }

    @Test
    @DisplayName("Recipe block header has 'already cooked'")
    void hasCooked() {
        card.find(byClassName("spoon")).shouldBe(visible);
    }

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

    //TODO debug that
    // 5. Recipe block footer has "Ingridients" block
    @Test
    @DisplayName("Recipe has has 'Ingridients' block")
    void hasIngridientsBlock() {
        System.out.println(card.$(byXpath("//div[contains(@class,'ui accordion')]")));
    }

}
