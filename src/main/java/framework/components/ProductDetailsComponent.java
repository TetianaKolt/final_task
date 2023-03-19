package framework.components;

import static framework.helpers.Helpers.getDigits;

import java.math.BigDecimal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class ProductDetailsComponent {

  private final WebElement image;
  private final String title;
  private final WebElement priceEl;
  private final BigDecimal price;
  private final String taxLabel;
  private final String productDescription;
  private final WebElement productVariants;
  private final WebElement selectProductOptions;
  private final WebElement productQuantityWanted;
  private final WebElement addToCartButton;
  private final WebElement addToWishListButton;


  public ProductDetailsComponent(WebElement container) {
    this.image = container.findElement(
        By.xpath(".//section[@id='content']//div[@class='product-cover']/img"));
    this.title = container.findElement
        (By.xpath(".//div[@class='col-md-6']//h1")).getText();
    this.priceEl = container.findElement
        (By.xpath(".//div[@class='current-price']/span"));
    this.price = getDigits(priceEl);
    this.taxLabel = container.findElement
        (By.xpath(".//div[@class='tax-shipping-delivery-label']")).getText().trim();
    this.productDescription = container.findElement(
        By.xpath(".//div[@id='product-description-short-17']")).getText();
    this.productVariants = container.findElement
        (By.xpath(".//div[@class='clearfix product-variants-item']//span"));
    this.selectProductOptions = container.findElement
        (By.xpath(".//select[@id='group_4']"));
    this.productQuantityWanted = container.findElement
        (By.xpath(".//input[@id='quantity_wanted']"));
    this.addToCartButton = container.findElement
        (By.xpath(".//button[@class='btn btn-primary add-to-cart']"));
    this.addToWishListButton = container.findElement
        (By.xpath(".//button[@class='wishlist-button-add wishlist-button-product']"));
  }
}
