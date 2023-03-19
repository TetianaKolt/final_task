package framework.components;

import static framework.helpers.Helpers.getDigits;

import java.math.BigDecimal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class CartModalWindowComponent {

  private String modalTitle;
  private WebElement productImage;
  private WebElement productName;
  private String productNameText;
  private WebElement productPriceEl;
  private BigDecimal productPrice;

  private String selectedOptions;
  private WebElement selectedQuantity;
  private String selectedQuantityStr;
  private String cartProductCountsText;
  private WebElement subTotalPriceEl;
  private BigDecimal subTotalPrice;
  private WebElement shippingValueEl;
  private String shippingValue;
  private WebElement totalValueEl;
  private BigDecimal totalValue;
  private WebElement continueShoppingButton;
  private WebElement proceedToCheckoutButton;

  public CartModalWindowComponent(WebElement container) {
    this.modalTitle = container.findElement
        (By.xpath(".//h4[@id='myModalLabel']/i")).getText();
    this.productImage = container.findElement
        (By.xpath(".//div[@class='col-md-6']/img"));
    this.productName = container.findElement
        (By.xpath(".//div[@class='col-md-6']/h6[@class='h6 product-name']"));
    this.productNameText = productName.getText();
    this.productPriceEl = container.findElement
        (By.xpath(".//div[@class='col-md-6']/p[@class='product-price']"));
    this.productPrice = getDigits(productPriceEl);
    this.selectedOptions = container.findElement
        (By.xpath(".//div[@class='col-md-6']/span")).getText();
    this.selectedQuantity = container.findElement
        (By.xpath(".//div[@class='col-md-6']//span[@class='product-quantity']"));
    this.selectedQuantityStr = selectedQuantity.getText();
    this.cartProductCountsText = container.findElement(
        By.xpath(".//div[@class='cart-content']//p[@class='cart-products-count']"))
        .getText();
    this.subTotalPriceEl = container.findElement
        (By.xpath(".//div[@class='cart-content']//span[@class='subtotal value']"));
    this.subTotalPrice = getDigits(subTotalPriceEl);
    this.shippingValueEl = container.findElement
        (By.xpath(".//div[@class='cart-content']//span[@class='shipping value']"));
    this.shippingValue = shippingValueEl.getText();
    this.totalValueEl = container.findElement
        (By.xpath(".//div[@class='cart-content']//span[@class='value']"));;
    this.totalValue = getDigits(totalValueEl);
    this.continueShoppingButton = container.findElement
        (By.xpath(".//div[@class='cart-content-btn']"
            + "//button[@class='btn btn-secondary']"));
    this.proceedToCheckoutButton = container.findElement
        (By.xpath(".//div[@class='cart-content-btn']//a[@class='btn btn-primary']"));
  }
}
