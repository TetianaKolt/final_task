package framework.pages;

import static framework.helpers.Helpers.isHighlightedInRed;

import framework.components.PersonalInformationComponents;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class CreateAnAccountPage extends BasePage {

  private final By firstNameInputLocator = By.id("field-firstname");
  private final By personalInfoContainerLocator = By.id("customer-form");
  private final By saveButtonLocator = By.xpath(
      "//button[@class='btn btn-primary form-control-submit float-xs-right']");
  private final By errorMessageLocator = By.xpath("//li[@class='alert alert-danger']");

  @Step("Get personal components")
  public PersonalInformationComponents getPersonalInformationComponents() {
    log.info("Get personal components");
    return new PersonalInformationComponents(find(personalInfoContainerLocator));
  }

  @Step("Fill first name as {firstName}")
  public CreateAnAccountPage fillFirstName(String firstName) {
    log.info("Fill first name");
    getPersonalInformationComponents().getFirstNameInput().sendKeys(firstName);
    return this;
  }

  @Step("Fill last name as {lastName}")
  public CreateAnAccountPage fillLastName(String lastName) {
    log.info("Fill last name");
    getPersonalInformationComponents().getLastNameInput().sendKeys(lastName);
    return this;
  }

  @Step("Fill email as {email}")
  public CreateAnAccountPage fillEmail(String email) {
    log.info("Fill email");
    getPersonalInformationComponents().getEmailInput().sendKeys(email);
    return this;
  }

  @Step("Fill password as {password}")
  public CreateAnAccountPage fillPassword(String password) {
    log.info("Fill password");
    getPersonalInformationComponents().getPasswordInput().sendKeys(password);
    return this;
  }

  @Step("Fill birth date as {date}")
  public CreateAnAccountPage fillBirthDate(String date) {
    log.info("Fill birth date");
    getPersonalInformationComponents().getBirthdateInput().sendKeys(date);
    return this;
  }

  @Step("Tick customer data privacy")
  public CreateAnAccountPage tickCustomerDataPrivacyCheckbox() {
    log.info("Tick customer data privacy");
    getPersonalInformationComponents().getCustomerDataPrivacyCheckBox().click();
    return this;
  }

  @Step("Tick 'I agree with...' checkbox")
  public CreateAnAccountPage tickIAgreeCheckbox() {
    log.info("Tick 'I agree with...' checkbox");
    getPersonalInformationComponents().getIAgreeCheckbox().click();
    return this;
  }

  @Step("Click 'save' button when invalid data is filled")
  public CreateAnAccountPage clickSaveButtonFail() {
    log.info("Click save");
    find(saveButtonLocator).click();
    return this;
  }

  @Step("Click 'save' button")
  public void clickSaveButtonPass() {
    find(saveButtonLocator).click();
  }

  @Step("Check if 'First name' field is highlighted in red")
  public boolean checkIfFirstNameIsHighlighted() {
    log.info("Check if 'First name' field is highlighted in red");
    return isHighlightedInRed(firstNameInputLocator, "outline-color");
  }

  @Step("Check if error message pops up")
  public boolean checkIfErrorPopsUp() {
    log.info("Check if error message pops up");
    return find(errorMessageLocator).isDisplayed();
  }

  @Step("Get text from error message")
  public String errorMessageGetText() {
    log.info("Get text from error message");
    return find(errorMessageLocator).getText();
  }

}
