package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MobileDriverConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class LocalMobileDriver implements WebDriverProvider {
  static MobileDriverConfig config = ConfigFactory.create(MobileDriverConfig.class, System.getProperties());
  public static URL getAppiumServerUrl() {
    try {
      return new URL(config.getLocalUrl());
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public WebDriver createDriver(Capabilities capabilities) {
    UiAutomator2Options options = new UiAutomator2Options();
    options.merge(capabilities);

    File app = getAppPath();
    options.setAutomationName(ANDROID_UIAUTOMATOR2)
        .setPlatformName(ANDROID)
        .setDeviceName(config.getDeviceName())
        .setPlatformVersion(config.getPlatformVersion())
        .setApp(app.getAbsolutePath())
        .setAppPackage(config.getAppPackage())
        .setAppActivity(config.getAppActivity());

    return new AndroidDriver(getAppiumServerUrl(), options);
  }
  // скачать приложение википедиа
  private File getAppPath() {
    String appUrl = "https://github.com/wikimedia/apps-android-wikipedia/" +
        "releases/download/latest/app-alpha-universal-release.apk";
    String appPath = "src/test/resources/apps/app-alpha-universal-release.apk";

    File app = new File(appPath);
    if (!app.exists()) {
      try (InputStream in = new URL(appUrl).openStream()) {
        copyInputStreamToFile(in, app);
      } catch (IOException e) {
        throw new AssertionError("Failed to download application", e);
      }
    }
    return app;
  }
}

