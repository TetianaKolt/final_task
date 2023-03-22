package framework.pages;

import framework.components.PersonalInformationComponents;
import org.openqa.selenium.By;


public class PersonalInformationPage extends BasePage{
  private final By personalInfoContainerLocator = By.id("checkout-personal-information-step");

  public PersonalInformationComponents getPersonalInformationComponents() {
    return new PersonalInformationComponents(find(personalInfoContainerLocator));
  }

  public PersonalInformationPage fillFirstName(String firstName) {
    getPersonalInformationComponents().getFirstNameInput().sendKeys(firstName);
    return this;
  }

  public PersonalInformationPage fillLastName(String lastName) {
    getPersonalInformationComponents().getLastNameInput().sendKeys(lastName);
    return this;
  }

  public PersonalInformationPage fillEmail(String email) {
    getPersonalInformationComponents().getEmailInput().sendKeys(email);
    return this;
  }


  public PersonalInformationPage fillBirthDate(String date) {
    getPersonalInformationComponents().getBirthdateInput().sendKeys(date);
    return this;
  }

  public PersonalInformationPage tickCustomerDataPrivacyCheckbox() {
    getPersonalInformationComponents().getCustomerDataPrivacyCheckBox().click();
    return this;
  }

  public PersonalInformationPage tickIAgreeCheckbox() {
    getPersonalInformationComponents().getIAgreeCheckbox().click();
    return this;
  }

  public PersonalInformationPage clickContinue() {
    getPersonalInformationComponents().getContinueButton().click();
    waitUntilPageIsLoaded();
    return this;
  }


}
