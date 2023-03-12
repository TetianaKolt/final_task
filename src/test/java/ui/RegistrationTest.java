package ui;

import static framework.helpers.FakeStringsHelper.generateFakeDate;
import static framework.helpers.FakeStringsHelper.generateFakeEmail;
import static framework.helpers.FakeStringsHelper.generateFakeLastName;
import static framework.helpers.FakeStringsHelper.generateFakePassword;

import framework.pages.CreateAnAccountPage;
import framework.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void registrationWithInvalidDataTest() {
    String firstName = "James8";

    CreateAnAccountPage accountPage = mainPage.clickOnSignInButton()
        .clickOnNoAccountLink()
        .fillFirstName(firstName)
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
        .as("Firstname " + firstName + " is not highlighted in red")
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
