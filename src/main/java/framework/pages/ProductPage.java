package framework.pages;

import framework.components.ProductDetailsComponent;
import framework.enums.ColorOptions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

  private final By containerLocator = By.id("wrapper");

  @Step
  public ProductDetailsComponent getProductDetailsComponents() {
    return new ProductDetailsComponent(find(containerLocator));
  }

  @Step
  public ProductPage chooseProductType(String textToSelect) {
    getProductDetailsComponents().getProductVariants().click();
    Select selectValue = new Select(getProductDetailsComponents().getSelectProductOptions());
    selectValue.selectByVisibleText(textToSelect);
    return this;
  }

  @Step
  public ProductPage changeQuantityTo(int quantity) {
    WebElement quantityEl = getProductDetailsComponents().getProductQuantityWanted();
    WebElement quantityButtonUp = getProductDetailsComponents().getButtonQuantityUp();
    quantityEl.clear();
    for (int i = 1; i < quantity; i++) {
      quantityButtonUp.click();
    }
    return this;
  }

  @Step
  public CartPage clickAddToCart() {
    waitUntilClickable(getProductDetailsComponents().getAddToCartButton(), 5);
    try {
      getProductDetailsComponents().getAddToCartButton().click();
    } catch (StaleElementReferenceException e) {
      getProductDetailsComponents().getAddToCartButton().click();
    }
    return new CartPage();
  }

  @Step
  public ProductPage customizeProduct(String phraseToCustomize) {
    getProductDetailsComponents().getProductCustomizationInput()
        .sendKeys(phraseToCustomize);
    getProductDetailsComponents().getSaveCustomizationButton().click();
    return this;
  }

  @Step
  public SearchResultsPage findAnotherProductByText(String productToFind) {
    MainPage mainPage = new MainPage();
    mainPage.searchProductByText(productToFind);
    return new SearchResultsPage();
  }

  @Step
  public ProductPage selectColor(ColorOptions color) {
    getProductDetailsComponents().getProductColorEl()
        .findElement(color.getLocator()).click();
    return this;
  }

}
