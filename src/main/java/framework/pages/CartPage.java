package framework.pages;

import static framework.helpers.Helpers.addSubtotalToShippingFee;

import framework.components.CartComponents;
import framework.components.CartModalWindowComponent;
import io.qameta.allure.Step;
import java.math.BigDecimal;
import org.openqa.selenium.By;


public class CartPage extends BasePage {

  private final By cartModalWindowLocator = By.xpath(
      "//div[@id='blockcart-modal']//div[@class='modal-content']");
  private final By cartContainerLocator = By.id("main");

  @Step
  public CartModalWindowComponent getCartModalWindowComponents() {
    getDriver().switchTo().activeElement();
    waitUntilVisible(cartModalWindowLocator, 5);
    return new CartModalWindowComponent(find(cartModalWindowLocator));
  }

  @Step
  public CartComponents getCartComponents() {
    return new CartComponents(find(cartContainerLocator));
  }

  @Step
  public ProductPage clickContinueShopping() {
    getCartModalWindowComponents()
        .getContinueShoppingButton()
        .click();
    return new ProductPage();
  }

  @Step
  public CartPage clickProceedToCheckout() {
    getCartModalWindowComponents()
        .getProceedToCheckoutButton()
        .click();
    return this;
  }

  @Step
  public PersonalInformationPage clickProceedToCheckoutToPersonalInfo() {
    getCartModalWindowComponents()
        .getProceedToCheckoutButton()
        .click();
    return new PersonalInformationPage();
  }

  @Step
  public BigDecimal calculateTotal() {
    return addSubtotalToShippingFee(
        getCartComponents().getPriceValue(),
        getCartComponents().getShippingValue());
  }

}
