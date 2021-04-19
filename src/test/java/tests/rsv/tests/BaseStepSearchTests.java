package tests.rsv.tests;

import Steps.BaseSteps;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

public class BaseStepSearchTests extends BaseSteps {
  private final static String PROJECT = "Проект";
  //private final static String BASE_URL = "https://rsv-test.bizml.ru";

  @Test
  public void PageSearchTest() {
    openMainPage();
    openSearchWindow();
    choseFilter(PROJECT);
    shouldHaveTestProject(PROJECT);
    shouldHaveTestLinkProject(PROJECT);
    shouldHaveTestLinkTextProject(PROJECT);


  }

}
