package framework.pages;

import framework.components.CartModalWindowComponent;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

  private final By cartModalWindowLocator = By.xpath(
      "//div[@id='blockcart-modal']//div[@class='modal-content']");

  public CartModalWindowComponent getCartModalWindowComponents() {
    return new CartModalWindowComponent(find(cartModalWindowLocator));
  }



}
