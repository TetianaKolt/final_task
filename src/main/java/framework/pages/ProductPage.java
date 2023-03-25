package framework.pages;

import static framework.helpers.Helpers.enterValueInSearchAndPressEnter;
import static framework.helpers.Helpers.scrollToElement;

import framework.components.HeaderComponents;
import framework.components.ProductDetailsComponent;
import framework.enums.ColorOptions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

  private final By containerLocator = By.id("wrapper");
  private final By headerContainerLocator = By.id("header");

  @Step("Get ProductDetailsComponents")
  public ProductDetailsComponent getProductDetailsComponents() {
    return new ProductDetailsComponent(find(containerLocator));
  }

  @Step("Get HeaderComponents")
  public HeaderComponents getHeaderComponents() {
    return new HeaderComponents(find(headerContainerLocator));
  }

  @Step("Choose product type as {textToSelect}")
  public ProductPage chooseProductType(String textToSelect) {
    getProductDetailsComponents().getProductVariants().click();
    Select selectValue = new Select(getProductDetailsComponents().getSelectProductOptions());
    selectValue.selectByVisibleText(textToSelect);
    return this;
  }

  @Step("Change quantity of product as {quantity}")
  public ProductPage changeQuantityTo(int quantity) {
    WebElement quantityEl = getProductDetailsComponents().getProductQuantityWanted();
    WebElement quantityButtonUp = getProductDetailsComponents().getButtonQuantityUp();
    quantityEl.clear();
    for (int i = 1; i < quantity; i++) {
      quantityButtonUp.click();
    }
    return this;
  }

  @Step("Click 'Add to cart'")
  public CartPage clickAddToCart() {
    waitUntilClickable(getProductDetailsComponents().getAddToCartButton(), 5);
    try {
      getProductDetailsComponents().getAddToCartButton().click();
    } catch (StaleElementReferenceException e) {
      getProductDetailsComponents().getAddToCartButton().click();
    }
    return new CartPage();
  }

  @Step("Customize product with text as {phraseToCustomize}")
  public ProductPage customizeProduct(String phraseToCustomize) {
    getProductDetailsComponents().getProductCustomizationInput().sendKeys(phraseToCustomize);
    getProductDetailsComponents().getSaveCustomizationButton().click();
    return this;
  }

  @Step("Find one more product by text as {productToFind}")
  public SearchResultsPage findAnotherProductByText(String productToFind) {
    WebElement searchField = getHeaderComponents().getSearchField();
    waitUntilVisible(searchField, 5);
    scrollToElement(searchField);
    enterValueInSearchAndPressEnter(searchField, productToFind);
    return new SearchResultsPage();
  }

  @Step("Select color of product as {color}")
  public ProductPage selectColor(ColorOptions color) {
    getProductDetailsComponents().getProductColorEl().findElement(color.getLocator()).click();
    return this;
  }

}
