package framework.pages;

import static framework.helpers.Helpers.scrollToElement;

import framework.components.PersonalInfoAddressesComponents;
import framework.components.PersonalInfoPaymentSection;
import framework.components.PersonalInfoShippingMethod;
import framework.components.PersonalInformationComponents;
import java.math.BigDecimal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class PersonalInformationPage extends BasePage {

  private final By personalInfoContainerLocator = By.id("checkout-personal-information-step");
  private final By personalInfoAddressContainerLocator = By.id("checkout-addresses-step");
  private final By personalInfoShippingContainerLocator = By.id("checkout-delivery-step");
  private final By personalInfoPaymentContainerLocator = By.id("content");

  public PersonalInfoPaymentSection getPersonalInfoPayment() {
    return new PersonalInfoPaymentSection(find(personalInfoPaymentContainerLocator));
  }

  public PersonalInfoShippingMethod getPersonalInfoShipping() {
    return new PersonalInfoShippingMethod(find(personalInfoShippingContainerLocator));
  }

  public PersonalInfoAddressesComponents getPersonalInfoAddressesComponents() {
    return new PersonalInfoAddressesComponents(find(personalInfoAddressContainerLocator));
  }

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

  public PersonalInformationPage clickContinueToAddress() {
    getPersonalInformationComponents().getContinueButton().click();
    return this;
  }

  public PersonalInformationPage fillInAddress(String address, String postalCode, String city) {
    getPersonalInfoAddressesComponents().getAddressInput().sendKeys(address);
    getPersonalInfoAddressesComponents().getZipPostalCodeInput().sendKeys(postalCode);
    getPersonalInfoAddressesComponents().getCityInput().sendKeys(city);
    return this;
  }

  public PersonalInformationPage clickContinueToShippingMethod() {
    getPersonalInfoAddressesComponents().getContinueButton().click();
    return this;
  }

  public PersonalInformationPage chooseRadioButtonMyCarrier() {
    getPersonalInfoShipping().getMyCarrierRadioButton().click();
    return this;
  }

  public PersonalInformationPage clickContinueToPayment() {
    getPersonalInfoShipping().getContinueButton().click();
    return this;
  }

  public BigDecimal selectPayByCheck() {
    WebElement payByCheck = getPersonalInfoPayment().getPayByCheckRadioButton();
    scrollToElement(payByCheck);
    payByCheck.click();
    return getPersonalInfoPayment().getTotalTaxInclSum();
  }

  public PersonalInformationPage clickIAgreeCheckBox() {
    getPersonalInfoPayment().getIAgreeCheckBox().click();
    return this;
  }

  public OrderConfirmationPage clickOnPlaceOrder() {
    getPersonalInfoPayment().getPlaceOrderButton().click();
    return new OrderConfirmationPage();
  }
}
