package tests.local;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBaseLocal {

  @BeforeAll
  static void beforeAll() {
    Configuration.browser = LocalMobileDriver.class.getName();
    Configuration.browserSize = null;
  }

  @BeforeEach
  void addListener() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    open();
  }

  @AfterEach
  void afterEach() {
//        Attach.screenshotAs("Last screenshot");
    Attach.pageSource();

    closeWebDriver();
  }
}
