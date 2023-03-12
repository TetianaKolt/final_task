package ui;

import static framework.DataForTests.DataForTests.userBirthDate;
import static framework.DataForTests.DataForTests.userEmail;
import static framework.DataForTests.DataForTests.userFirstName;
import static framework.DataForTests.DataForTests.userLastName;
import static framework.DataForTests.DataForTests.userPassword;

import framework.components.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class RegistrationWithValidDataTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void registrationWithValidDataTest() {

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

    WebElement nameNearTheCart = mainPage.checkNameNearCart();

//    Check your name appear near cart button
    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(nameNearTheCart.isDisplayed())
        .as("Registered user name is not displayed near the cart after registration")
        .isTrue();
    softAssertions.assertThat(nameNearTheCart.getText())
        .as("Name is not the same as registered: " + "[" + userFirstName + "] "
            + "[" + userLastName + "]")
        .isEqualTo(userFirstName + " " + userLastName);
    softAssertions.assertAll();

  }
}
