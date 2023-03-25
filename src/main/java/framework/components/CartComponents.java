package framework.components;

import static framework.helpers.Helpers.getDigits;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class CartComponents {

  private final List<WebElement> cartProductContainerLocator;
  private final WebElement totalQuantityInCartEl;
  private final BigDecimal totalQuantityInCart;
  private final WebElement priceValueEl;
  private final BigDecimal priceValue;
  private final WebElement shippingValueEl;
  private final BigDecimal shippingValue;
  private final WebElement totalSumEl;
  private final BigDecimal totalSum;
  private final WebElement continueShoppingButton;
  private final WebElement proceedToCheckoutButton;

  public CartComponents(WebElement cartContainer) {
    this.cartProductContainerLocator = cartContainer.findElements(
        By.xpath("//li[@class='cart-item']"));
    this.totalQuantityInCartEl = cartContainer.findElement(
        By.xpath("//span[@class='label js-subtotal']"));
    this.totalQuantityInCart = getDigits(totalQuantityInCartEl);
    this.priceValueEl = cartContainer.findElement(
        By.xpath("//div[@id='cart-subtotal-products']//span[@class='value']"));
    this.priceValue = getDigits(priceValueEl);
    this.shippingValueEl = cartContainer.findElement(
        By.xpath("//div[@id='cart-subtotal-shipping']//span[@class='value']"));
    this.shippingValue = getDigits(shippingValueEl);
    this.totalSumEl = cartContainer.findElement(
        By.xpath("//div[@class='cart-summary-line cart-total']//span[@class='value']"));
    this.totalSum = getDigits(totalSumEl);
    this.continueShoppingButton = cartContainer.findElement(
        By.xpath("//a[@class='label']/i[@class='material-icons']"));
    this.proceedToCheckoutButton = cartContainer.findElement(
        By.xpath("//a[contains(text(),'checkout')]"));
  }

  @Getter
  public static class CartItemsComponents {

    private final WebElement image;
    private final String productName;
    private final WebElement productPriceEl;
    private final BigDecimal productPrice;
    private final WebElement quantity;
    private final WebElement productCalculatedPriceEL;
    private final BigDecimal productCalculatedPrice;
    private final WebElement deleteButton;

    public CartItemsComponents(WebElement cartProductContainer) {
      this.image = cartProductContainer.findElement(
          By.xpath(".//span[@class='product-image media-middle']/img"));
      this.productName = cartProductContainer.findElement(
          By.xpath(".//div[@class='product-line-info']/a")).getText();
      this.productPriceEl = cartProductContainer.findElement(
          By.xpath(".//div[@class='current-price']/span"));
      this.productPrice = getDigits(productPriceEl);
      this.quantity = cartProductContainer.findElement(
          By.xpath(".//input[@class='js-cart-line-product-quantity form-control']"));
      this.productCalculatedPriceEL = cartProductContainer.findElement(
          By.xpath(".//span[@class='product-price']/strong"));
      this.productCalculatedPrice = getDigits(productCalculatedPriceEL);
      this.deleteButton = cartProductContainer.findElement(
          By.xpath(".//a[@class='remove-from-cart']"));
    }
  }

}
