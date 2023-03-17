package tests.local;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SearchLocalTest extends TestBaseLocal {
  @Test
  @Tag("android_emulator")
  @DisplayName("Successful search on Android")
  void successfulSearchAndroidTest() {
    step("Skip onboarding screen", () ->
        $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
    step("Type search", () -> {
      $(AppiumBy.accessibilityId("Search Wikipedia")).click();
      $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
    });
    step("Verify content found", () ->
        $$(id("org.wikipedia.alpha:id/page_list_item_title"))
            .shouldHave(sizeGreaterThan(0)));
  }

  @Test
  @Tag("android_emulator")
  @DisplayName("Checking the loading screen of the first page")
  void onboardingScreen2() {
    step("open the app", () ->
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia\n" +
            "…in over 300 languages")));

  }

  @Test
  @Tag("android_emulator")
  @DisplayName("Checking the second page loading screen")
  void continueOnboardingScreen() {
    step("Switching to the second page continuation screen", () ->
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
    step("Verify content found", () ->
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore")));
  }

  @Test
  @Tag("android_emulator")
  @DisplayName("Checking the third page loading screen")
  void doubleContinueOnboardingScreen() {
    step("Go to the third page continuation screen", () -> {
      $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
      $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
    });
    step("Verify content found", () ->
        $(id("org.wikipedia.alpha:id/primaryTextView"))
            .shouldHave(text("Reading lists with sync")));
  }

  @Test
  @Tag("android_emulator")
  @DisplayName("Checking the loading screen of the fourth page")
  void threeContinueOnboardingScreen() {
    step("Go to the fourth page continuation screen", () -> {
      $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
      $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
      $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
    });
    step("Verify content found", () ->
        $(id("org.wikipedia.alpha:id/primaryTextView"))
            .shouldHave(text("Send anonymous data")));
  }
}