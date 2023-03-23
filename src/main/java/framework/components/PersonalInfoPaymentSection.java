package framework.components;

import static framework.helpers.Helpers.getDigits;

import java.math.BigDecimal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class PersonalInfoPaymentSection {
  private final WebElement payByCheckRadioButton;
  private final WebElement payByBankWireRadioButton;
  private final WebElement amount;
  private final BigDecimal amountSum;
  private final WebElement iAgreeCheckBox;
  private final WebElement placeOrderButton;
  /// right menu elements:
  private final WebElement subTotal;
  private final BigDecimal subTotalSum;
  private final WebElement shipping;
  private final BigDecimal shippingSum;
  private final WebElement totalTaxIncl;
  private final BigDecimal totalTaxInclSum;

  public PersonalInfoPaymentSection(WebElement container) {
    this.payByCheckRadioButton = container.findElement(
        By.xpath("//label[@for='payment-option-1']"));
    this.payByBankWireRadioButton = container.findElement(
        By.xpath("//label[@for='payment-option-2']"));
    this.amount = container.findElement(By.xpath(
        "//div[@id='payment-option-1-additional-information']"
            + "//dd[contains(text(),'tax incl')]"));
    this.amountSum = getDigits(amount);
    this.iAgreeCheckBox = container.findElement(
        By.id("conditions_to_approve[terms-and-conditions]"));
    this.placeOrderButton = container.findElement(
        By.xpath("//div[@id='payment-confirmation']//button[@type='submit']"));

    ///// right menu
    this.subTotal = container.findElement(By.xpath(
        "//div[@id='cart-subtotal-products']//span[@class='value']"));
    this.subTotalSum = getDigits(subTotal);
    this.shipping = container.findElement(By.xpath(
        "//div[@id='cart-subtotal-shipping']//span[@class='value']"));
    this.shippingSum = getDigits(shipping);
    this.totalTaxIncl = container.findElement(By.xpath(
        "//div[@class='cart-summary-line cart-total']//span[@class='value']"));
    this.totalTaxInclSum = getDigits(totalTaxIncl);
  }
}
