package framework.components;

import static framework.helpers.Helpers.getDigits;

import java.math.BigDecimal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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


  public ProductComponents(WebElement container) {

    ///// ??????????????

    this.productTitleElement = container.findElement
        (By.xpath(".//*[@class='h3 product-title']/a"));

    this.productTitleText = productTitleElement.getText();

    try {
      this.productImageElement = container.findElement(
          By.xpath(".//div[@class='thumbnail-top']"));
    } catch (
        Exception e) {
      productImageElement = null;
    }

    try {
      this.productDiscountTag = container.findElement(
          By.xpath(".//li[@class='product-flag discount']"));
    } catch (
        NoSuchElementException e) {
      productDiscountTag = null;
    }
    try {
      this.productDiscountTagText = getDigits(productDiscountTag);
    } catch (
        NullPointerException e) {
      productDiscountTagText = null;
    }

    try {
      this.productRegularPrice = container.findElement
          (By.xpath(".//span[@class='regular-price']"));
    } catch (
        Exception e) {
      productRegularPrice = null;
    }
    try {
      this.productRegularPriceText = getDigits(productRegularPrice);
    } catch (
        NullPointerException e) {
      productRegularPriceText = null;
    }

    try {
      this.productNewPrice = container.findElement(By.xpath(".//*[@class='price']"));
    } catch (
        Exception e) {
      this.productNewPrice = null;
    }
    try {
      this.productNewPriceText = getDigits(productNewPrice);
    } catch (
        NullPointerException e) {
      this.productNewPriceText = null;
    }
  }


}
