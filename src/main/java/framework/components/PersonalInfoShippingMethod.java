package framework.components;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class PersonalInfoShippingMethod {

  private final WebElement prestaShopRadioButton;
  private final WebElement prestaShopRadioButtonPrice;
  private final WebElement myCarrierRadioButton;
  private final WebElement myCarrierRadioButtonPrice;
  private final WebElement continueButton;

  public PersonalInfoShippingMethod(WebElement container) {
    this.prestaShopRadioButton = container.findElement(By.id("delivery_option_1"));
    this.prestaShopRadioButtonPrice = container.findElement(
        By.xpath("//label[@for='delivery_option_1']//span[@class='carrier-price']"));
    this.myCarrierRadioButton = container.findElement(By.id("delivery_option_2"));
    this.myCarrierRadioButtonPrice = container.findElement(
        By.xpath("//label[@for='delivery_option_2']//span[@class='carrier-price']"));
    this.continueButton = container.findElement(
        By.xpath("//form[@id='js-delivery']//button[@type='submit']"));
  }
}
