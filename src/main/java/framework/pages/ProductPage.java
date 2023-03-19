package framework.pages;

import framework.components.ProductDetailsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

  private final By containerLocator = By.id("wrapper");

  public ProductDetailsComponent getProductDetailsComponents() {
    return new ProductDetailsComponent(find(containerLocator));
  }

  public ProductPage chooseProductType(String textToSelect) {
    getProductDetailsComponents().getProductVariants().click();
    Select selectValue = new Select(getProductDetailsComponents().getSelectProductOptions());
    selectValue.selectByVisibleText(textToSelect);
    return this;
  }

  public ProductPage changeQuantityTo(int quantity) {
    WebElement quantityEl = getProductDetailsComponents().getProductQuantityWanted();
    quantityEl.clear();
    quantityEl.sendKeys(String.valueOf(quantity));
    return this;
  }

  public CartPage clickAddToCart() {
    getProductDetailsComponents().getAddToCartButton().click();
    return new CartPage();
  }
}
