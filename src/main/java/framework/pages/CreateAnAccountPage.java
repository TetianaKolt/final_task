package framework.pages;

import static framework.helpers.Helpers.isHighlightedInRed;

import framework.components.PersonalInformationComponents;
import org.openqa.selenium.By;


public class CreateAnAccountPage extends BasePage {

  private final By firstNameInputLocator = By.id("field-firstname");
  private final By personalInfoContainerLocator = By.id("customer-form");
  private final By saveButtonLocator = By.xpath(
      "//button[@class='btn btn-primary form-control-submit float-xs-right']");
  private final By errorMessageLocator = By.xpath("//li[@class='alert alert-danger']");

  public PersonalInformationComponents getPersonalInformationComponents() {
    return new PersonalInformationComponents(find(personalInfoContainerLocator));
  }


  public CreateAnAccountPage fillFirstName(String firstName) {
    getPersonalInformationComponents().getFirstNameInput().sendKeys(firstName);
    return this;
  }

  public CreateAnAccountPage fillLastName(String lastName) {
    getPersonalInformationComponents().getLastNameInput().sendKeys(lastName);
    return this;
  }

  public CreateAnAccountPage fillEmail(String email) {
    getPersonalInformationComponents().getEmailInput().sendKeys(email);
    return this;
  }

  public CreateAnAccountPage fillPassword(String password) {
    getPersonalInformationComponents().getPasswordInput().sendKeys(password);
    return this;
  }

  public CreateAnAccountPage fillBirthDate(String date) {
    getPersonalInformationComponents().getBirthdateInput().sendKeys(date);
    return this;
  }

  public CreateAnAccountPage tickCustomerDataPrivacyCheckbox() {
    getPersonalInformationComponents().getCustomerDataPrivacyCheckBox().click();
    return this;
  }

  public CreateAnAccountPage tickIAgreeCheckbox() {
    getPersonalInformationComponents().getIAgreeCheckbox().click();
    return this;
  }

  public CreateAnAccountPage clickSaveButtonFail() {
    find(saveButtonLocator).click();
    return this;
  }

  public CreateAnAccountPage clickSaveButtonPass() {
    find(saveButtonLocator).click();
    return this;
  }


  public boolean checkIfFirstNameIsHighlighted() {
    return isHighlightedInRed(firstNameInputLocator, "outline-color");
  }

  public boolean checkIfErrorPopsUp() {
    return find(errorMessageLocator).isDisplayed();
  }

  public String errorMessageGetText() {
    return find(errorMessageLocator).getText();
  }

}
