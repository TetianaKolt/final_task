package framework.components;

import static framework.helpers.Helpers.getDigits;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class CartComponents {

  private List<WebElement> cartProductContainerLocator;
  private WebElement totalQuantityInCartEl;
  private BigDecimal totalQuantityInCart;
  private WebElement priceValueEl;
  private BigDecimal priceValue;
  private WebElement shippingValueEl;
  private BigDecimal shippingValue;
  private WebElement totalSumEl;
  private BigDecimal totalSum;
  private WebElement continueShoppingButton;
  private WebElement proceedToCheckoutButton;

  public CartComponents(WebElement cartContainer) {
    /////
    this.cartProductContainerLocator = cartContainer.findElements(
        By.xpath("//li[@class='cart-item']"));
    //////

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

    private WebElement image;
    private String productName;
    private WebElement productPriceEl;
    private BigDecimal productPrice;
    private WebElement quantity;
    private WebElement productCalculatedPriceEL;
    private BigDecimal productCalculatedPrice;
    private WebElement deleteButton;

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
          By.xpath(".//span[@class='product-price']/strong")
      );
      this.productCalculatedPrice = getDigits(productCalculatedPriceEL);
      this.deleteButton = cartProductContainer.findElement(
          By.xpath(".//a[@class='remove-from-cart']"));
    }
  }

}
