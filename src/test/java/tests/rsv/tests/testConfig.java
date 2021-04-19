package tests.rsv.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class testConfig {
  @BeforeAll
  static void setUp() {
    //System.setProperty("selenide.browser", "firefox");
    Configuration.startMaximized = true;
    addListener("AllureSelenide", new AllureSelenide());
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("browserName", "chrome");
    capabilities.setCapability("browserVersion", "89.0");
    capabilities.setCapability("enableVNC", true);
    Configuration.browserCapabilities = capabilities;
    System.setProperty("remote.web.driver", "http://127.0.0.1:4444/wd/hub");
    String remoteWebDriver = System.getProperty("remote.web.driver");
    System.out.println(remoteWebDriver);
    if (remoteWebDriver != null) {
      Configuration.remote = remoteWebDriver;
    }
  }

  @Test
  public void testCong() {
    open("https://rsv-test.bizml.ru/");
    /*System.setProperty("selenide.browser", "chrome");
    String remoteWebDriver = System.getProperty("selenide.browser");
    System.out.println(remoteWebDriver);*/
  }
}
