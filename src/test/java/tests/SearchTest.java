package tests;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SearchTest extends TestBase {

  @DisplayName("Successful search on Android")
  @Tag("android")
  @Test
  void successfulSearchAndroidTest() {
    step("Type search", () -> {
      $(accessibilityId("Search Wikipedia")).click();
      $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
    });
    step("Verify content found", () ->
        $$(id("org.wikipedia.alpha:id/page_list_item_title"))
            .shouldHave(sizeGreaterThan(0)));
  }

  @DisplayName("Checking the information on the link click")
  @Tag("android")
  @Test
  void openTheLinkTest() {
    step("click on the link on the main page", () -> {
      $(id("org.wikipedia.alpha:id/horizontal_scroll_list_item_text")).click();
    });
    step("Verify content found", () ->
        $(id("org.wikipedia.alpha:id/view_news_fullscreen_story_text"))
            .shouldHave(text(
                "At the Academy Awards, Everything Everywhere All at Once wins seven awards," +
                    " including Best Picture and Best Director for Daniel Kwan and Daniel " +
                    "Scheinert (both pictured).")));
  }

  @DisplayName("Successful search on iPhone")
  @Tag("Iphone")
  @Test
  void successfulSearchIosTest() {

    step("Type search", () -> {
      $(accessibilityId("Text Button")).click();
      $(accessibilityId("Text Input")).sendKeys("hello@browserstack.com");
    });
    step("Verify content found", () ->
        $(accessibilityId("Text Output")).shouldBe(visible));

  }
}
