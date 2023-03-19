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
  private final WebElement productPrice;
  private final BigDecimal productPriceText;
//  private final WebElement addToWishListButton;

  public ProductComponents(WebElement container) {
    this.productTitleElement = container.findElement
//        (By.xpath(".//h3[@class='h3 product-title']"));
    (By.xpath(".//*[@class='h3 product-title']"));
    this.productTitleText = productTitleElement.getText();

    ///// ??????????????
    try {
      this.productImageElement = container.findElement(
          By.xpath(".//div[@class='thumbnail-top']"));
    } catch (NoSuchElementException e) {
      productImageElement = null;
    }

    try {
      this.productDiscountTag = container.findElement(
          By.xpath(".//ul[@class='product-flags js-product-flags']/li"));
    } catch (NoSuchElementException e) {
      productDiscountTag = null;
    }
    try {
      this.productDiscountTagText = getDigits(productDiscountTag);
    } catch (NullPointerException e) {
      productDiscountTagText = null;
    }

    try {
      this.productRegularPrice = container.findElement
          (By.xpath(".//span[@class='regular-price']"));
    } catch (NoSuchElementException e) {
      productRegularPrice = null;
    }
    try {
      this.productRegularPriceText = getDigits(productRegularPrice);
    } catch (NullPointerException e) {
      productRegularPriceText = null;
    }
    this.productPrice = container.findElement(By.xpath(".//span[@class='price']"));
    this.productPriceText = getDigits(productPrice);
//    this.addToWishListButton = container.findElement(
//        By.xpath(".//button[@class='wishlist-button-add']"));
  }




}
