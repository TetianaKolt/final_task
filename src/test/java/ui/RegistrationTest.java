package ui;

import static framework.DataForTests.DataForTests.userBirthDate;
import static framework.DataForTests.DataForTests.userEmail;
import static framework.DataForTests.DataForTests.userFirstName;
import static framework.DataForTests.DataForTests.userLastName;
import static framework.DataForTests.DataForTests.userPassword;
import static framework.helpers.FakeStringsHelper.generateFakeDate;
import static framework.helpers.FakeStringsHelper.generateFakeEmail;
import static framework.helpers.FakeStringsHelper.generateFakeLastName;
import static framework.helpers.FakeStringsHelper.generateFakePassword;

import framework.components.pages.CreateAnAccountPage;
import framework.components.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void registrationWithValidDataTest() {
//    Click on 'Sign in' button at the right corner of the page
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

  @Test
  public void registrationWithInvalidDataTest() {
    String fakeFirstName = "James8";

    CreateAnAccountPage accountPage = mainPage.clickOnSignInButton()
        .clickOnNoAccountLink()
        .fillFirstName(fakeFirstName)
        .fillLastName(generateFakeLastName())
        .fillEmail(generateFakeEmail())
        .fillPassword(generateFakePassword())
        .fillBirthDate(generateFakeDate())
        .tickCustomerDataPrivacyCheckbox()
        .tickIAgreeCheckbox()
        .clickSaveButtonFail();

    boolean actualColor = accountPage.firstNameIsHighlighted();

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualColor)
        .as("Firstname " + fakeFirstName + " is not highlighted in red")
        .isTrue();

    //Check that pop-up with text 'Invalid name' appear under field
    boolean errorMessageIsPresent = accountPage.checkIfErrorPopsUp();

    softAssertions.assertThat(errorMessageIsPresent)
        .as("Error pop-up is not present")
        .isTrue();

    String actualErrorMessageText = accountPage.errorMessageGetText();
    String expectedErrorMessageText = "Invalid name";

    softAssertions.assertThat(actualErrorMessageText)
        .as("The error text is not as expected")
        .isEqualTo(expectedErrorMessageText);

    softAssertions.assertAll();
  }


}
