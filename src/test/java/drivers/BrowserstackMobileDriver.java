package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MobileDriverConfig;
import java.net.MalformedURLException;
import java.net.URL;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserstackMobileDriver implements WebDriverProvider {
  MobileDriverConfig config =
      ConfigFactory.create(MobileDriverConfig.class, System.getProperties());

  @Override
  public WebDriver createDriver(Capabilities capabilities) {

    String user = config.getUser();
    String password = config.getPassword();
    String app = config.getApp();
    String remoteUrl = config.getRemoteURL();
    String device = config.getDevice();
    String version = config.getOsVersion();
    String nameProject = config.getProject();
    String build = config.getBuild();
    String nameTest = config.getName();

    MutableCapabilities mutableCapabilities = new MutableCapabilities();
    mutableCapabilities.merge(capabilities);

    mutableCapabilities.setCapability("browserstack.user", user);
    mutableCapabilities.setCapability("browserstack.key", password);
    mutableCapabilities.setCapability("app", app);
    mutableCapabilities.setCapability("device", device);
    mutableCapabilities.setCapability("os_version", version);
    mutableCapabilities.setCapability("project", nameProject);
    mutableCapabilities.setCapability("build", build);
    mutableCapabilities.setCapability("name", nameTest);


    try {
      return new RemoteWebDriver(
          new URL(remoteUrl), mutableCapabilities);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
