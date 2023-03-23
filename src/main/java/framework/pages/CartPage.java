package framework.pages;

import static framework.helpers.Helpers.addSubtotalToShippingFee;

import framework.components.CartComponents;
import framework.components.CartModalWindowComponent;
import java.math.BigDecimal;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

  private final By cartModalWindowLocator = By.xpath(
      "//div[@id='blockcart-modal']//div[@class='modal-content']");
  private final By cartContainerLocator = By.id("main");

  public CartModalWindowComponent getCartModalWindowComponents() {
    getDriver().switchTo().activeElement();
    waitUntilPageIsLoaded();
//    waitUntilVisible(cartModalWindowLocator, 5);
    Wait wait = new WebDriverWait(getDriver(), 5);
    wait.until(ExpectedConditions.presenceOfElementLocated(cartModalWindowLocator));
    return new CartModalWindowComponent(find(cartModalWindowLocator));
  }

  public CartComponents getCartComponents() {
    return new CartComponents(find(cartContainerLocator));
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

  public PersonalInformationPage clickProceedToCheckoutToPersonalInfo() {
    getCartModalWindowComponents()
        .getProceedToCheckoutButton()
        .click();
    return new PersonalInformationPage();
  }


  public BigDecimal checkTotalIsCorrect() {
    return addSubtotalToShippingFee(
        getCartComponents().getPriceValue(),
        getCartComponents().getShippingValue());
  }

}
