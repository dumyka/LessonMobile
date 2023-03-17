package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {
  public static String env = System.getProperty("env");

  @BeforeAll
  static void beforeAll() {

   /* if (env == "android") {
      Configuration.browser = BrowserstackMobileDriver.class.getName();
    } else if (env == "iphone") {
      Configuration.browser = BrowserstackMobileDriver.class.getName();
    } else if (env == "android_emulator") {
      Configuration.browser = LocalMobileDriver.class.getName();
    }*/
    if (env == null) {
      env = "android";
    }

    switch (env) {
      case "android_emulator":
        Configuration.browser = LocalMobileDriver.class.getName();
        break;
      case "android":
        Configuration.browser = BrowserstackMobileDriver.class.getName();
        break;
      case "iphone":
        Configuration.browser = BrowserstackMobileDriver.class.getName();
        break;

    }
    //Configuration.browser = BrowserstackMobileDriver.class.getName();
    Configuration.browserSize = null;
  }

  @BeforeEach
  void addListener() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    open();
  }

  @AfterEach
  void afterEach() {
    String sessionId = Selenide.sessionId().toString();

    //Attach.screenshotAs("Last screenshot");
    Attach.pageSource();

    closeWebDriver();
    //Attach.addVideo(sessionId);
    switch (env) {
      case "android":
        Attach.addVideo(sessionId);
        break;
      case "iphone":
        Attach.addVideo(sessionId);
        break;
    }
  }
}
