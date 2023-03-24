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
  private final By getCartWindowLocator= By.xpath("//div[@class='cart-grid row']");
  private final By cartContainerLocator = By.id("main");

  @Step
  public CartModalWindowComponent getCartModalWindowComponents() {
    waitUntilVisible(cartModalWindowLocator,10);
    getDriver().switchTo().activeElement();
    return new CartModalWindowComponent(find(cartModalWindowLocator));
  }

  @Step
  public CartComponents getCartComponents() {
    return new CartComponents(find(getCartWindowLocator));
  }

  @Step
  public ProductPage clickContinueShopping() {
    WebElement continueShopping = getCartModalWindowComponents().getContinueShoppingButton();
    waitUntilVisible(continueShopping, 5);
    scrollToElement(continueShopping);
    continueShopping
        .click();
    return new ProductPage();
  }

  @Step
  public CartPage clickProceedToCheckout() {
    WebElement proceed = getCartModalWindowComponents().getProceedToCheckoutButton();
    waitUntilVisible(proceed,5);
    proceed.click();
    return this;
  }

  @Step
  public PersonalInformationPage clickProceedToCheckoutToPersonalInfo() {
    WebElement proceed = getCartComponents().getProceedToCheckoutButton();
    scrollToElement(proceed);
    proceed.click();
    return new PersonalInformationPage();
  }

  @Step
  public BigDecimal calculateTotal() {
    return addSubtotalToShippingFee(
        getCartComponents().getPriceValue(),
        getCartComponents().getShippingValue());
  }

}
