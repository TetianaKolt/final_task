package framework.components;

import static framework.helpers.Helpers.getDigits;

import java.math.BigDecimal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class CartModalWindowComponent {

  private final String modalTitle;
  private final WebElement productImage;
  private final WebElement productName;
  private final String productNameText;
  private final WebElement productPriceEl;
  private final BigDecimal productPrice;

  private final String selectedOptions;
  private final WebElement selectedQuantity;
  private final BigDecimal selectedQuantityInt;
  private final String cartProductCountsText;
  private final WebElement subTotalPriceEl;
  private final BigDecimal subTotalPrice;
  private final WebElement shippingValueEl;
  private final BigDecimal shippingValue;
  private final WebElement totalSumEl;
  private final BigDecimal totalSum;
  private final WebElement continueShoppingButton;
  private final WebElement proceedToCheckoutButton;

  public CartModalWindowComponent(WebElement container) {
    this.modalTitle = container.findElement
        (By.xpath("//h4[@id='myModalLabel']")).getText();
    this.productImage = container.findElement
        (By.xpath("//div[@class='col-md-6']/img"));
    this.productName = container.findElement
        (By.xpath("//div[@class='col-md-6']/h6[@class='h6 product-name']"));
    this.productNameText = productName.getText();
    this.productPriceEl = container.findElement
        (By.xpath("//div[@class='col-md-6']/p[@class='product-price']"));
    this.productPrice = getDigits(productPriceEl);
    this.selectedOptions = container.findElement
        (By.xpath("//div[@class='col-md-6']/span/strong")).getText();
    this.selectedQuantity = container.findElement
        (By.xpath("//div[@class='col-md-6']//span[@class='product-quantity']/strong"));
    this.selectedQuantityInt = getDigits(selectedQuantity);
    this.cartProductCountsText = container.findElement(
        By.xpath("//div[@class='cart-content']//p[@class='cart-products-count']"))
        .getText();
    this.subTotalPriceEl = container.findElement
        (By.xpath("//div[@class='cart-content']//span[@class='subtotal value']"));
    this.subTotalPrice = getDigits(subTotalPriceEl);
    this.shippingValueEl = container.findElement
        (By.xpath("//div[@class='cart-content']//span[@class='shipping value']"));
    this.shippingValue = getDigits(shippingValueEl);
    this.totalSumEl = container.findElement
        (By.xpath("//div[@class='cart-content']//span[@class='value']"));;
    this.totalSum = getDigits(totalSumEl);
    this.continueShoppingButton = container.findElement
        (By.xpath("//div[@class='cart-content-btn']"
            + "//button[@class='btn btn-secondary']"));
    this.proceedToCheckoutButton = container.findElement
        (By.xpath("//div[@class='cart-content-btn']//a[@class='btn btn-primary']"));
  }
}
