package framework.components;

import static framework.helpers.Helpers.getDigits;

import java.math.BigDecimal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Getter
public class ProductDetailsComponent {

  private final WebElement image;
  private final String title;
  private final WebElement priceEl;
  private final BigDecimal price;
  private final String taxLabel;
  private final WebElement productQuantityWanted;
  private final WebElement buttonQuantityUp;
  private final WebElement buttonQuantityDown;
  private final WebElement addToCartButton;
  private final WebElement addToWishListButton;
  private String productDescription;
  private WebElement productVariants;
  private WebElement selectProductOptions;
  private WebElement productCustomizationInput;
  private WebElement saveCustomizationButton;
  private WebElement productColorEl;


  public ProductDetailsComponent(WebElement container) {
    this.image = container.findElement(
        By.xpath("//section[@id='content']//div[@class='product-cover']/img"));
    this.title = container.findElement
        (By.xpath("//div[@class='col-md-6']//h1")).getText();
    this.priceEl = container.findElement
        (By.xpath("//div[@class='current-price']/span"));
    this.price = getDigits(priceEl);
    this.taxLabel = container.findElement
        (By.xpath("//div[@class='tax-shipping-delivery-label']")).getText().trim();
    try {
      this.productDescription = container.findElement(
          By.xpath("//div[@id='product-description-short-17']")).getText();
    } catch (NoSuchElementException e) {
      this.productDescription = null;
    }
    try {
      this.productVariants = container.findElement
          (By.xpath("//div[@class='clearfix product-variants-item']//span"));
    } catch (Exception e) {
      this.productVariants = null;
    }
    try {
      this.selectProductOptions = container.findElement(
          By.xpath("//select[@id='group_4']"));
    } catch (Exception e) {
      this.selectProductOptions = null;
    }

    try {
      this.productCustomizationInput = container.findElement(
          By.xpath("//textarea[@id='field-textField1']"));
    } catch (Exception e) {
      this.productCustomizationInput = null;
    }

    try {
      this.saveCustomizationButton = container.findElement(
          By.xpath("//button[@name='submitCustomizedData']"));
    } catch (Exception e) {
      this.saveCustomizationButton = null;
    }

    try {
      this.productColorEl = container.findElement(
          By.xpath("//span[contains(text(),'Color')]/parent::div"));
    } catch (Exception e) {
      this.productColorEl = null;
    }

    this.productQuantityWanted = container.findElement(
        By.xpath("//input[@id='quantity_wanted']"));

    this.buttonQuantityUp = container.findElement(
        By.xpath(".//button[@class='btn btn-touchspin "
            + "js-touchspin bootstrap-touchspin-up']"));
    this.buttonQuantityDown = container.findElement(
        By.xpath(".//button[@class='btn btn-touchspin "
            + "js-touchspin bootstrap-touchspin-down']"));

    this.addToCartButton = container.findElement(
        By.xpath("//button[@class='btn btn-primary add-to-cart']"));
    this.addToWishListButton = container.findElement(
        By.xpath("//button[@class='wishlist-button-add wishlist-button-product']"));
  }
}
