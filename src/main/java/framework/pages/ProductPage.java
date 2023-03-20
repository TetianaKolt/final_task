package framework.pages;

import framework.components.ProductDetailsComponent;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
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
///////// ????????????????????????????????????????
//    quantityEl.clear();
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("arguments[0].value = '';", quantityEl);
    quantityEl.sendKeys(String.valueOf(quantity));
    return this;
  }

  public CartPage clickAddToCart() {
    try {
      getProductDetailsComponents().getAddToCartButton().click();
    } catch (StaleElementReferenceException e) {
      getProductDetailsComponents().getAddToCartButton().click();
    }
    return new CartPage();
  }
}
