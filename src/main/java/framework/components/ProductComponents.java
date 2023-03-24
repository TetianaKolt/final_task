package framework.components;

import static framework.helpers.Helpers.getDigits;

import framework.pages.BasePage;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class ProductComponents {

  private final WebElement productTitleElement;
  private final String productTitleText;
  private WebElement productImageElement;
  private WebElement productDiscountTag;
  private BigDecimal productDiscountTagText;
  private WebElement productRegularPrice;
  private BigDecimal productRegularPriceText;
  private WebElement productNewPrice;
  private BigDecimal productNewPriceText;

  @SneakyThrows
  public ProductComponents(WebElement container) {

    try {
      BasePage.waitUntilPresent(By.xpath("//*[@class='price']"), 5);
      this.productNewPrice = container.findElement(By.xpath(".//*[@class='price']"));
    } catch (Exception e) {
      this.productNewPrice = null;
    }
    try {
      this.productNewPriceText = getDigits(productNewPrice);
    } catch (NullPointerException e) {
      this.productNewPriceText = null;
    }

    try {
      this.productRegularPrice = container.findElement(By.xpath(
          ".//span[@class='regular-price']"));
    } catch (Exception e) {
      this.productRegularPrice = this.productNewPrice;
    }
    try {
      this.productRegularPriceText = getDigits(productRegularPrice);
    } catch (Exception e) {
      productRegularPriceText = this.productNewPriceText;
    }

    this.productTitleElement = container.findElement(
        By.xpath(".//h2[@class='h3 product-title']"));
    this.productTitleText = productTitleElement.getText();

    try {
      this.productImageElement = container.findElement(By.xpath(
          ".//div[@class='thumbnail-top']"));
    } catch (Exception e) {
      productImageElement = null;
    }

    try {
      this.productDiscountTag = container.findElement(
          By.xpath(".//li[@class='product-flag discount']"));
    } catch (Exception e) {
      productDiscountTag = null;
    }
    try {
      this.productDiscountTagText = getDigits(productDiscountTag);
    } catch (NullPointerException e) {
      productDiscountTagText = null;
    }

  }

}
