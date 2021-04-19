package Steps;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class BaseSteps {
  private final static String BASE_URL = "https://rsv-test.bizml.ru/";
  private static String SelenideBrowser;
  private static String remoteWebDriver;

  @BeforeAll
  static void setUp() {
    System.setProperty("selenide.browser", System.getProperty("browserName"));
    //SelenideBrowser = System.getProperty("selenide.browser");
    Configuration.startMaximized = true;
    addListener("AllureSelenide", new AllureSelenide());
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("browserName", System.getProperty("browserName"));
    capabilities.setCapability("browserVersion", System.getProperty("browserVersion"));
    capabilities.setCapability("enableVNC", true);
    System.setProperty("remote.web.driver", "http://127.0.0.1:4444/wd/hub");
    remoteWebDriver = System.getProperty("remote.web.driver");
    System.out.println(remoteWebDriver);
    if (remoteWebDriver != null) {
      Configuration.remote = remoteWebDriver;
    }
  }

  @AfterEach
  void afterEach() {
    attachScreenshot("Last screenshot");
    attachPageSource();
    if (System.getProperty("browserName").equals("chrome") || System.getProperty("selenide.browser").equals("chrome")) {
      attachAsText("Browser console logs", getConsoleLogs());
    }
    closeWebDriver();
  }

  @Step("Открываем главную странцу")
  public void openMainPage() {
    open(BASE_URL);
  }

  @Step("открываем окно поиска")
  public void openSearchWindow() {
    $(".search-button").click();
    $(".searching-input").clear();
  }

  @Step("выбираем фильтр")
  public void choseFilter(final String PROJECT) {
    $(".searching-input").val(PROJECT);
    $(".searching-switcher").click();
    $$(".categories-column").filterBy(text(PROJECT + "ы")).first().click();
  }

  @Step("Проверка фильтрации по проектам")
  public void shouldHaveTestProject(final String PROJECT) {
    $$(".results.SRWrapper.results").first().shouldHave(text(PROJECT + "ы"));
  }

  @Step("Проверка,что есть ссылка на раздел Проекты")
  public void shouldHaveTestLinkProject(final String PROJECT) {
    $(".results-item").shouldHave(text(PROJECT + "ы" + "\n" + "Перейти на страницу проектов"));
  }

  @Step("Проверка,что работает переход на страницу проектов")
  public void shouldHaveTestLinkTextProject(final String PROJECT) {
    $(".results-item").shouldHave(text(PROJECT + "ы" + "\n" + "Перейти на страницу проектов"));
  }
}
