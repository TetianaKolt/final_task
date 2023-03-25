package framework.pages;

import static framework.helpers.Helpers.ifAlertIsPresent;
import static framework.helpers.Helpers.scrollToElement;

import framework.components.PersonalInfoAddressesComponents;
import framework.components.PersonalInfoPaymentSection;
import framework.components.PersonalInfoShippingMethod;
import framework.components.PersonalInformationComponents;
import io.qameta.allure.Step;
import java.math.BigDecimal;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class PersonalInformationPage extends BasePage {

  private final By personalInfoContainerLocator = By.id("checkout-personal-information-step");
  private final By personalInfoAddressContainerLocator = By.id("checkout-addresses-step");
  private final By personalInfoShippingContainerLocator = By.id("checkout-delivery-step");
  private final By personalInfoPaymentContainerLocator = By.id("content");

  @Step("Get PersonalInfoPaymentSection components")
  public PersonalInfoPaymentSection getPersonalInfoPayment() {
    return new PersonalInfoPaymentSection(find(personalInfoPaymentContainerLocator));
  }

  @Step("Get PersonalInfoShippingMethod components")
  public PersonalInfoShippingMethod getPersonalInfoShipping() {
    return new PersonalInfoShippingMethod(find(personalInfoShippingContainerLocator));
  }

  @Step("Get PersonalInfoAddressesComponents")
  public PersonalInfoAddressesComponents getPersonalInfoAddressesComponents() {
    return new PersonalInfoAddressesComponents(find(personalInfoAddressContainerLocator));
  }

  @Step("Get PersonalInformationComponents")
  public PersonalInformationComponents getPersonalInformationComponents() {
    return new PersonalInformationComponents(find(personalInfoContainerLocator));
  }

  @Step("Fill first name as {firstName}")
  public PersonalInformationPage fillFirstName(String firstName) {
    getPersonalInformationComponents().getFirstNameInput().sendKeys(firstName);
    return this;
  }

  @Step("Fill last name as {lastName}")
  public PersonalInformationPage fillLastName(String lastName) {
    getPersonalInformationComponents().getLastNameInput().sendKeys(lastName);
    return this;
  }

  @Step("Fill email as {email}")
  public PersonalInformationPage fillEmail(String email) {
    getPersonalInformationComponents().getEmailInput().sendKeys(email);
    return this;
  }

  @Step("Fill birth date as {date}")
  public PersonalInformationPage fillBirthDate(String date) {
    getPersonalInformationComponents().getBirthdateInput().sendKeys(date);
    return this;
  }

  @Step("Tick customer data privacy")
  public PersonalInformationPage tickCustomerDataPrivacyCheckbox() {
    getPersonalInformationComponents().getCustomerDataPrivacyCheckBox().click();
    return this;
  }

  @Step("Tick 'I agree with...' checkbox")
  public PersonalInformationPage tickIAgreeCheckbox() {
    getPersonalInformationComponents().getIAgreeCheckbox().click();
    return this;
  }

  @Step("Click continue to proceed to address info")
  public PersonalInformationPage clickContinueToAddress() {
    getPersonalInformationComponents().getContinueButton().click();
    return this;
  }

  @Step("Fill in address with {address}, {postalCode}, {city}")
  public PersonalInformationPage fillInAddress(String address, String postalCode, String city) {
    getPersonalInfoAddressesComponents().getAddressInput().sendKeys(address);
    getPersonalInfoAddressesComponents().getZipPostalCodeInput().sendKeys(postalCode);
    getPersonalInfoAddressesComponents().getCityInput().sendKeys(city);
    return this;
  }

  @Step("Click continue to shipping method")
  public PersonalInformationPage clickContinueToShippingMethod() {
    WebElement continueButton = getPersonalInfoAddressesComponents().getContinueButton();
    scrollToElement(continueButton);
    try {
      continueButton.click();
    } catch (ElementNotInteractableException e) {
      JavascriptExecutor executor = (JavascriptExecutor) getDriver();
      executor.executeScript("arguments[0].click();", continueButton);
    }
    ifAlertIsPresent();
    waitUntilVisible(personalInfoShippingContainerLocator, 10);
    return this;
  }


  @Step("Choose 'My Carrier' radio button")
  public PersonalInformationPage chooseRadioButtonMyCarrier() {
    WebElement radioButton = getPersonalInfoShipping().getMyCarrierRadioButton();
    waitUntilVisible(radioButton, 5);
    try {
      radioButton.click();
    } catch (ElementNotInteractableException e) {
      JavascriptExecutor executor = (JavascriptExecutor) getDriver();
      executor.executeScript("arguments[0].click();", radioButton);
    }
    return this;
  }

  @Step("Click Continue to payment")
  public PersonalInformationPage clickContinueToPayment() {
    getPersonalInfoShipping().getContinueButton().click();
    return this;
  }

  @Step("Select to pay by check")
  public BigDecimal selectPayByCheck() {
    WebElement payByCheck = getPersonalInfoPayment().getPayByCheckRadioButton();
    scrollToElement(payByCheck);
    payByCheck.click();
    return getPersonalInfoPayment().getTotalTaxInclSum();
  }

  @Step("Click 'I agree ...' checkbox")
  public PersonalInformationPage clickIAgreeCheckBox() {
    getPersonalInfoPayment().getIAgreeCheckBox().click();
    return this;
  }

  @Step("Click on 'Place order' button")
  public OrderConfirmationPage clickOnPlaceOrder() {
    getPersonalInfoPayment().getPlaceOrderButton().click();
    return new OrderConfirmationPage();
  }
}
