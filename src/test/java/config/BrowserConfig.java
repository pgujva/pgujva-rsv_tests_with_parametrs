package config;

import org.aeonbits.owner.Config;
@Config.Sources({"classpath:browser.config/default.properties"})
public interface BrowserConfig extends Config {

  @Key("browser.name")
  String browsername();

  @Key("browser.version")
  String browserversion();

  @Key("browser.location")
  String browserlocation();
}
