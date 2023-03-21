package framework.pages;

import static framework.helpers.Helpers.checkTotalCalculationPriceQuantity;
import static framework.helpers.Helpers.checkTotalCalculationSubtotalShippingFee;

import framework.components.CartComponents;
import framework.components.CartModalWindowComponent;
import java.math.BigDecimal;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

  private final By cartModalWindowLocator = By.xpath(
      "//div[@id='blockcart-modal']//div[@class='modal-content']");
  private final By cartContainerLocator = By.id("main");

  public CartModalWindowComponent getCartModalWindowComponents() {
    waitUntilPresent(cartModalWindowLocator, 3);
    getDriver().switchTo().activeElement();
    waitUntilVisible(cartModalWindowLocator, 5);
    return new CartModalWindowComponent(find(cartModalWindowLocator));
  }

  public ProductPage clickContinueShopping() {
    getCartModalWindowComponents()
        .getContinueShoppingButton()
        .click();
    return new ProductPage();
  }

  public CartPage clickProceedToCheckout() {
    getCartModalWindowComponents()
        .getProceedToCheckoutButton()
        .click();
    return this;
  }

  public CartComponents getCartComponents() {
    return new CartComponents(find(cartContainerLocator));
  }

  public BigDecimal checkTotalIsCorrect() {
    return checkTotalCalculationSubtotalShippingFee(
        getCartComponents().getPriceValue(),
        getCartComponents().getShippingValue());
  }

}
