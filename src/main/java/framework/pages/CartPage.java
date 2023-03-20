package framework.pages;

import framework.components.CartModalWindowComponent;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

  private final By cartModalWindowLocator = By.xpath(
      "//div[@id='blockcart-modal']//div[@class='modal-content']");

  public CartModalWindowComponent getCartModalWindowComponents() {
    waitUntilPresent(cartModalWindowLocator,3);
    getDriver().switchTo().activeElement();
    waitUntilVisible(cartModalWindowLocator,5);
    return new CartModalWindowComponent(find(cartModalWindowLocator));
  }



}
