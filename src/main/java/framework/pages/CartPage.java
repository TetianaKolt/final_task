package framework.pages;

import static framework.helpers.Helpers.addSubtotalToShippingFee;
import static framework.helpers.Helpers.scrollToElement;

import framework.components.CartComponents;
import framework.components.CartModalWindowComponent;
import io.qameta.allure.Step;
import java.math.BigDecimal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class CartPage extends BasePage {

  private final By cartModalWindowLocator = By.xpath("//div[@id='blockcart-modal']");
  private final By getCartWindowLocator = By.xpath("//div[@class='cart-grid row']");

  @Step("Get CartModalWindow components")
  public CartModalWindowComponent getCartModalWindowComponents() {
    waitUntilVisible(cartModalWindowLocator, 10);
    getDriver().switchTo().activeElement();
    return new CartModalWindowComponent(find(cartModalWindowLocator));
  }

  @Step("Get Cart components")
  public CartComponents getCartComponents() {
    return new CartComponents(find(getCartWindowLocator));
  }

  @Step("Click 'continue shopping'")
  public ProductPage clickContinueShopping() {
    WebElement continueShopping = getCartModalWindowComponents().getContinueShoppingButton();
    waitUntilVisible(continueShopping, 5);
    scrollToElement(continueShopping);
    continueShopping
        .click();
    return new ProductPage();
  }

  @Step("Click 'proceed to checkout'")
  public CartPage clickProceedToCheckout() {
    WebElement proceed = getCartModalWindowComponents().getProceedToCheckoutButton();
    waitUntilVisible(proceed, 5);
    proceed.click();
    return this;
  }

  @Step("Click 'Proceed to checkout' to move to personal info")
  public PersonalInformationPage clickProceedToCheckoutToPersonalInfo() {
    WebElement proceed = getCartComponents().getProceedToCheckoutButton();
    scrollToElement(proceed);
    proceed.click();
    return new PersonalInformationPage();
  }

  // @Step is in Helpers
  public BigDecimal calculateTotal() {
    return addSubtotalToShippingFee(
        getCartComponents().getPriceValue(),
        getCartComponents().getShippingValue());
  }

}
