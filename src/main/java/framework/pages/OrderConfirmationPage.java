package framework.pages;

import static framework.helpers.Helpers.getDigits;

import java.math.BigDecimal;
import org.openqa.selenium.By;

public class OrderConfirmationPage extends BasePage {

  private final By titleOrderIsConfirmed = By.xpath("//h3[@class='h1 card-title']");
  private final By subTotal = By.xpath(
      "//td[contains(text(),'Subtotal')]/following-sibling::td");
  private final By shippingAndHandling = By.xpath(
      "//td[contains(text(),'Shipping and handling')]/following-sibling::td");
  private final By totalTaxIncl = By.xpath(
      "//tr[@class='total-value font-weight-bold']/td[2]");


  public String getTitleName() {
    return find(titleOrderIsConfirmed).getText().substring(1);
  }

  public BigDecimal getSubTotal() {
    return getDigits(find(subTotal));
  }

  public BigDecimal getShippingAndHandlingPrice() {
    return getDigits(find(shippingAndHandling));
  }

  public BigDecimal getTotalTaxIncl() {
    return getDigits(find(totalTaxIncl));
  }

}
