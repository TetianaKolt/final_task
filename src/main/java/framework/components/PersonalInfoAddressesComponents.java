package framework.components;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Getter
public class PersonalInfoAddressesComponents {
  private WebElement firstNameInput;
  private WebElement lastNameInput;
  private WebElement companyInput;
  private WebElement VATNumberInput;
  private WebElement addressInput;
  private WebElement addressComplementInput;
  private WebElement zipPostalCodeInput;
  private WebElement cityInput;
  private WebElement countryDropDownBox;
  private WebElement phoneInput;
  private WebElement checkBoxUseThisAddress;
  private WebElement continueButton;


  public PersonalInfoAddressesComponents(WebElement container) {
    this.firstNameInput = container.findElement(By.xpath("//*[@id='field-firstname']"));
    this.lastNameInput = container.findElement(By.xpath("//*[@id='field-lastname']"));
    this.companyInput = container.findElement(By.xpath("//*[@id='field-email']"));
    this.VATNumberInput = container.findElement(By.xpath("//*[@id='field-password']"));
    this.addressInput = container.findElement(By.xpath("//*[@id='field-birthday']"));
    this.addressComplementInput = container.findElement(By.xpath(".//input[@name='optin']"));
    this.zipPostalCodeInput = container.findElement(
        By.xpath("//input[@name='customer_privacy']"));
    this.cityInput = container.findElement(By.xpath("//input[@name='psgdpr']"));
    try {
      this.phoneInput = container.findElement(By.xpath(
          "//footer[@class='form-footer clearfix']"
              + "//button[@class='continue btn btn-primary float-xs-right']"));
    }catch (NoSuchElementException e){
      this.phoneInput = null;
    }
    this.countryDropDownBox = container.findElement(
        By.xpath("//input[@name='customer_privacy']"));

    this.checkBoxUseThisAddress = container.findElement(
        By.xpath("//input[@name='customer_privacy']"));

    this.continueButton = container.findElement(
        By.xpath("//input[@name='customer_privacy']"));
  }


}
