package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:user.config/login.properties"})
public interface LoginConfig extends Config {
  @Key("login")
  String userLogin();

  @Key("password")
  String userPassword();
}
