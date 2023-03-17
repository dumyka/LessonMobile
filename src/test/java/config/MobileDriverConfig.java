package config;

import org.aeonbits.owner.Config;

@Config.Sources({
    //"system:properties",
   // "classpath:android_emulator.properties"
    "classpath:${env}.properties"
    //"classpath:android.properties"
})
public interface MobileDriverConfig extends Config {

  @Key("user")
  String getUser();

  @Key("password")
  String getPassword();

  @Key("app")
  String getApp();

  @Key("device")
  String getDevice();

  @Key("project")
  String getProject();

  @Key("build")
  String getBuild();

  @Key("name")
  String getName();

  @Key("osVersion")
  String getOsVersion();

  @Key("remoteUrl")
  String getRemoteURL();


  @Key("localURL")
  String getLocalUrl();

  @Key("platformName")
  String getPlatformName();

  @Key("deviceName")
  String getDeviceName();

  @Key("platformVersion")
  String getPlatformVersion();


  @Key("appPackage")
  String getAppPackage();

  @Key("appActivity")
  String getAppActivity();
}
