package tests.rsv.tests;

import Steps.BaseSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Text;
import config.BrowserConfig;
import config.LoginConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestPrivateData{

  LoginConfig loginConfig = ConfigFactory.create(LoginConfig.class);
  BrowserConfig browserConfig = ConfigFactory.create(BrowserConfig.class);

  //private static String remoteWebDriver;
  @BeforeAll
  static void setUp() {
    System.out.println();
   // System.setProperty("selenide.browser", "firefox");
    Configuration.browser = "firefox";
    Configuration.startMaximized = true;
    Configuration.remote = "http://127.0.0.1:4444/wd/hub";
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("browserName", "firefox");
    capabilities.setCapability("browserVersion", "87.0");
    capabilities.setCapability("enableVNC", true);
    Configuration.browserCapabilities = capabilities;

    //System.setProperty("remote.web.driver", "http://127.0.0.1:4444/wd/hub");
    //remoteWebDriver = System.getProperty("remote.web.driver");

  }

  @Test
  public void loginTest() {
    System.out.println(browserConfig.browsername());
    System.out.println(browserConfig.browserversion());
    System.out.println(browserConfig.browserlocation());
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
