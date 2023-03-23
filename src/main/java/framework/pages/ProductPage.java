package framework.pages;

import framework.components.HeaderComponents;
import framework.components.ProductDetailsComponent;
import framework.enums.ColorOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

  private final By containerLocator = By.id("wrapper");
  private final By headerContainer = By.id("header");

  public HeaderComponents getHeaderComponents() {
    return new HeaderComponents(find(headerContainer));
  }

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
    WebElement quantityButtonUp = getProductDetailsComponents().getButtonQuantityUp();

/////// ????????????????????????????????????????
    quantityEl.clear();
    for (int i = 0; i < quantity; i++) {
      quantityButtonUp.click();
    }

//    JavascriptExecutor js = (JavascriptExecutor) getDriver();
//    js.executeScript("arguments[0].value = '';", quantityEl);

//    quantityEl.sendKeys(String.valueOf(quantity));
    return this;
  }

  public CartPage clickAddToCart() {
    Wait wait = new WebDriverWait(getDriver(), 5);
    wait.until(ExpectedConditions.elementToBeClickable(getProductDetailsComponents().getAddToCartButton()));
    try {
      getProductDetailsComponents().getAddToCartButton().click();
    } catch (StaleElementReferenceException e) {
      getProductDetailsComponents().getAddToCartButton().click();
    }
    return new CartPage();
  }

  public ProductPage customizeProduct(String phraseToCustomize) {
    getProductDetailsComponents().getProductCustomizationInput()
        .sendKeys(phraseToCustomize);
    getProductDetailsComponents().getSaveCustomizationButton().click();
    return this;
  }

  public SearchResultsPage findAnotherProductByText(String productToFind) {
    MainPage mainPage = new MainPage();
    mainPage.searchProductByText(productToFind);
    return new SearchResultsPage();
  }

  public ProductPage selectColor(ColorOptions color) {
    getProductDetailsComponents().getProductColorEl()
        .findElement(By.xpath("//label[@aria-label='" + color.getColorName()
            + "']/input")).click();
    return this;
  }

}
