package framework.helpers;

import static framework.pages.BasePage.find;
import static framework.pages.BasePage.waitUntilPageIsLoaded;
import static framework.pages.BasePage.waitUntilVisible;

import framework.components.ProductComponents;
import framework.pages.BasePage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Helpers {

  // hover over element
  @Step("Hover over element {locator}")
  public static void hoverOverElement(By locator) {
    WebElement element = find(locator);
    Actions actions = new Actions(BasePage.getDriver());
    actions.moveToElement(element).build().perform();
  }


  // scroll to element
  public static void scrollToElement(WebElement element) {
    JavascriptExecutor jse = (JavascriptExecutor) BasePage.getDriver();
    jse.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  // check if highlighted in red
  public static boolean isHighlightedInRed(By locator, String cssValue) {
    return find(locator).getCssValue(cssValue).equals("rgba(255, 76, 76, 1)");
  }

  // take a screenshot
  @Attachment(value = "{fileName}", type = "image/png")
  public static byte[] takeScreenShot(String fileName) {
    return ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.BYTES);
  }

  //Get digits from WebElement
  public static BigDecimal getDigits(WebElement el) {
    waitUntilPageIsLoaded();
    String text = el.getText();
    String price = text.replaceAll("[^\\d.]", "");
    if (price.equals("")) {
      return BigDecimal.ZERO;
    }
    return new BigDecimal(price);
  }

  // Check TOTAL calculation
  @Step("Calculate [total] using values {originalPrice}, {quantity}, {shippingFee}")
  public static BigDecimal multiplyPriceByQuantityAddShipping(BigDecimal originalPrice,
      BigDecimal quantity, BigDecimal shippingFee) {
    return originalPrice.multiply(quantity).add(shippingFee);
  }

  // Check TOTAL calculation
  @Step("Calculate [total] using values {subTotal} and {shippingFee}")
  public static BigDecimal addSubtotalToShippingFee(BigDecimal subTotal, BigDecimal shippingFee) {
    return subTotal.add(shippingFee);
  }

  @Step("Calculate [discounted price]")
  public static List<BigDecimal> calculateDiscountedPrice(List<ProductComponents> products) {
    return products.stream().map(product -> {
      BigDecimal productDiscount = product.getProductDiscountTagText();
      BigDecimal regularPrice = product.getProductRegularPriceText();
      return regularPrice.subtract(regularPrice.multiply(productDiscount)
          .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN));
    }).collect(Collectors.toList());
  }

  // SearchField - press enter to search
  @Step("Enter {productToFind} in search field and press enter")
  public static void enterValueInSearchAndPressEnter(WebElement searchField, String productToFind) {
    Actions actions = new Actions(BasePage.getDriver());
    actions.moveToElement(searchField).click().sendKeys(productToFind).sendKeys(Keys.ENTER).build()
        .perform();
  }

  //// Get all products
  @Step("Get list of all products")
  public static List<ProductComponents> getAllProducts(By containerLocator) {
    List<ProductComponents> products = new ArrayList<>();

    waitUntilPageIsLoaded();
    scrollToElement(find(containerLocator));
    List<WebElement> containers = BasePage.findAll(containerLocator);

    waitUntilVisible(containerLocator, 5);

    for (WebElement container : containers) {
      ProductComponents productComponents = new ProductComponents(container);
      products.add(productComponents);
    }
    return products;
  }
}
