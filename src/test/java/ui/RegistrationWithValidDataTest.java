package ui;

import static framework.helpers.FakeStringsHelper.generateFakeDate;
import static framework.helpers.FakeStringsHelper.generateFakeEmail;
import static framework.helpers.FakeStringsHelper.generateFakeFirstName;
import static framework.helpers.FakeStringsHelper.generateFakeLastName;
import static framework.helpers.FakeStringsHelper.generateFakePassword;

import framework.pages.MainPage;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class RegistrationWithValidDataTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void registrationWithValidDataTest() {

    String userFirstName = generateFakeFirstName();
    String userLastName = generateFakeLastName();
    String userEmail = generateFakeEmail();
    String userPassword = generateFakePassword();
    String userBirthDate = generateFakeDate();

    mainPage.clickOnSignInButton()
        .clickOnNoAccountLink()
        .fillFirstName(userFirstName)
        .fillLastName(userLastName)
        .fillEmail(userEmail)
        .fillPassword(userPassword)
        .fillBirthDate(userBirthDate)
        .tickCustomerDataPrivacyCheckbox()
        .tickIAgreeCheckbox()
        .clickSaveButtonPass();

    String nameNearTheCart = mainPage.checkNameNearCart();

//    Check your name appear near cart button

    Assertions.assertThat(nameNearTheCart)
        .as("Registered user name is not displayed near the cart after registration")
        .isEqualTo(userFirstName + " " + userLastName);
  }
}
