package tests.rsv.tests;

import Steps.BaseSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Text;
import config.BrowserConfig;
import config.LoginConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class TestPrivateData {

  LoginConfig loginConfig = ConfigFactory.create(LoginConfig.class);
  static BrowserConfig browserConfig = ConfigFactory.create(BrowserConfig.class);

  @BeforeAll
  static void setUp() {
    addListener("AllureSelenide", new AllureSelenide());
    Configuration.browser = "chrome";
    Configuration.startMaximized = true;
    if (!browserConfig.browserlocation().equals("http://test/wd/hub")) {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("browserName", browserConfig.browsername());
      capabilities.setCapability("browserVersion", browserConfig.browserversion());
      capabilities.setCapability("enableVNC", true);
      Configuration.browserCapabilities = capabilities;
      Configuration.remote = browserConfig.browserlocation();
    }

  }

  @Test
  public void loginTest() {
    open("https://rsv-test.bizml.ru/");
    $(".auth").click();
    $("#email").click();
    $("#email").clear();
    $("#email").val(loginConfig.userLogin());
    $("#password").click();
    $("#password").clear();
    $("#password").val(loginConfig.userPassword());
    $("#login-form-submit").click();
    $(".buttons").shouldHave(text("тест"));


  }
}
