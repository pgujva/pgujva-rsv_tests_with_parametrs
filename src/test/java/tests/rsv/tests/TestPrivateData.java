package tests.rsv.tests;

import Steps.BaseSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Text;
import config.LoginConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestPrivateData{

  LoginConfig loginConfig = ConfigFactory.create(LoginConfig.class);

  @BeforeEach
  public void setUp() {
    Configuration.startMaximized = true;

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
