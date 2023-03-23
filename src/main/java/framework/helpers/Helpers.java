package framework.helpers;

import static framework.pages.BasePage.find;

import com.github.javafaker.Faker;
import framework.components.ProductComponents;
import framework.pages.BasePage;
import io.qameta.allure.Attachment;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Helpers {

  // hover over element
  public static void hoverOverElement(WebElement element) {
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

  // Take a screenshot
  @SneakyThrows
  public static void makeScreenShot() {
    File scrFile = ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile,
        new File("/Users/AIM/IdeaProjects/pageObjectLab/src/test/resources/screenshots"
            + new Faker().random().hex(10) + ".png"));
  }

  @Attachment(value = "{fileName}", type = "image/png")
  public static byte[] takeScreenShot(String fileName) {
    return ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.BYTES);
  }

  //Get digits from WebElement
  public static BigDecimal getDigits(WebElement el) {
    String text = el.getText();
    String price = text.replaceAll("[^\\d.]", "");
    if (price.equals("")) {
      return BigDecimal.ZERO;
    }
    return new BigDecimal(price);
  }

  // Check TOTAL calculation
  public static BigDecimal checkTotalCalculationPriceQuantity(BigDecimal originalPrice,
      BigDecimal quantity, BigDecimal shippingFee) {
    return originalPrice
        .multiply(quantity)
        .add(shippingFee);
  }

  public static BigDecimal checkTotalCalculationSubtotalShippingFee(BigDecimal subTotal,
      BigDecimal shippingFee) {
    return subTotal.add(shippingFee);
  }

  //// Get all products
  public static List<ProductComponents> getAllProducts(By containerLocator) {
    List<ProductComponents> products = new ArrayList<>();
    List<WebElement> containers = BasePage.findAll(containerLocator);

    for (WebElement container : containers) {
      ProductComponents productComponents = new ProductComponents(container);
      products.add(productComponents);
    }
    return products;
  }

  public static List<BigDecimal> checkCalculationOfDiscountedPrice(
      List<ProductComponents> products) {
    return products.stream()
        .map(product -> {
          BigDecimal productDiscount = product.getProductDiscountTagText();
          BigDecimal regularPrice = product.getProductRegularPriceText();
          return regularPrice.subtract(regularPrice.multiply(productDiscount)
              .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN));
        })
        .collect(Collectors.toList());
  }

}
