package ui;

import static framework.helpers.FakeStringsHelper.generateFakeDate;
import static framework.helpers.FakeStringsHelper.generateFakeEmail;
import static framework.helpers.FakeStringsHelper.generateFakeLastName;
import static framework.helpers.FakeStringsHelper.generateFakePassword;

import framework.pages.CreateAnAccountPage;
import framework.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class RegistrationWithInvalidDataTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void registrationWithInvalidDataTest() {
    String fakeFirstName = "James8";

    CreateAnAccountPage accountPage = mainPage
        .clickOnSignInButton()
        .clickOnNoAccountLink()
        .fillFirstName(fakeFirstName)
        .fillLastName(generateFakeLastName())
        .fillEmail(generateFakeEmail())
        .fillPassword(generateFakePassword())
        .fillBirthDate(generateFakeDate())
        .tickCustomerDataPrivacyCheckbox()
        .tickIAgreeCheckbox()
        .clickSaveButtonFail();

    boolean isFieldHighlighted = accountPage.checkIfFirstNameIsHighlighted();

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(isFieldHighlighted)
        .as("Firstname " + fakeFirstName + " is not highlighted in red")
        .isTrue();

    //Check that pop-up with text 'Invalid name' appear under field
    boolean isErrorMessagePresent = accountPage.checkIfErrorPopsUp();

    softAssertions.assertThat(isErrorMessagePresent)
        .as("Error pop-up is not present")
        .isTrue();

    String actualErrorMessageText = accountPage.errorMessageGetText();
    String expectedErrorMessageText = "Invalid format.";

    softAssertions.assertThat(actualErrorMessageText)
        .as("The error text is not as expected")
        .isEqualTo(expectedErrorMessageText);

    softAssertions.assertAll();
  }
}
