package framework.pages;

import static framework.helpers.Helpers.isHighlightedInRed;

import org.openqa.selenium.By;

public class CreateAnAccountPage extends BasePage {

  private final By socialTitleRadioButtonMRLocator = By.id("field-id_gender-1");
  private final By socialTitleRadioButtonMSLocator = By.id("field-id_gender-2");
  private final By firstNameInputLocator = By.id("field-firstname");
  private final By lastNameInputLocator = By.id("field-lastname");
  private final By emailInputLocator = By.id("field-email");
  private final By passwordInputLocator = By.id("field-password");
  private final By birthdateInputLocator = By.id("field-birthday");
  private final By receiveOffersCheckBox = By.xpath("//input[@name='optin']");
  private final By customerDataPrivacyCheckBoxLocator = By.xpath(
      "//input[@name='customer_privacy']");
  private final By iAgreeCheckboxLocator = By.xpath("//input[@name='psgdpr']");
  private final By saveButtonLocator = By.xpath(
      "//button[@class='btn btn-primary form-control-submit float-xs-right']");
  private final By errorMessageLocator = By.xpath("//li[@class='alert alert-danger']");

  public CreateAnAccountPage fillFirstName(String firstName) {
    find(firstNameInputLocator).sendKeys(firstName);
    return this;
  }

  public CreateAnAccountPage fillLastName(String lastName) {
    find(lastNameInputLocator).sendKeys(lastName);
    return this;
  }

  public CreateAnAccountPage fillEmail(String email) {
    find(emailInputLocator).sendKeys(email);
    return this;
  }

  public CreateAnAccountPage fillPassword(String password) {
    find(passwordInputLocator).sendKeys(password);
    return this;
  }

  public CreateAnAccountPage fillBirthDate(String date) {
    find(birthdateInputLocator).sendKeys(date);
    return this;
  }

  public CreateAnAccountPage tickCustomerDataPrivacyCheckbox() {
    find(customerDataPrivacyCheckBoxLocator).click();
    return this;
  }

  public CreateAnAccountPage tickIAgreeCheckbox() {
    find(iAgreeCheckboxLocator).click();
    return this;
  }

  public CreateAnAccountPage clickSaveButtonFail() {
    find(saveButtonLocator).click();
    return this;
  }

  public boolean firstNameIsHighlighted() {
    return isHighlightedInRed(firstNameInputLocator, "outline-color");
  }

  public boolean checkIfErrorPopsUp(){
    return find(errorMessageLocator).isDisplayed();
  }
  public String errorMessageGetText(){
   return find(errorMessageLocator).getText();
  }

}
