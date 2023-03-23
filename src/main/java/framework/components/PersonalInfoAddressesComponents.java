package framework.components;

import lombok.Getter;
import org.openqa.selenium.By;
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
    this.firstNameInput = container.findElement(
        By.xpath("//*[@id='field-firstname']"));
    this.lastNameInput = container.findElement(
        By.xpath("//*[@id='field-lastname']"));
    this.companyInput = container.findElement(By.xpath("//*[@id='field-company']"));
    this.VATNumberInput = container.findElement(
        By.xpath("//*[@id='field-vat_number']"));
    this.addressInput = container.findElement(By.xpath("//*[@id='field-address1']"));
    this.addressComplementInput = container.findElement(
        By.xpath("//*[@id='field-address2']"));
    this.zipPostalCodeInput = container.findElement(
        By.xpath("//*[@id='field-postcode']"));
    this.cityInput = container.findElement(By.xpath("//*[@id='field-city']"));
    this.countryDropDownBox = container.findElement(
        By.xpath("//select[@id='field-id_country']"));
    this.phoneInput = container.findElement(By.xpath("//*[@id='field-phone']"));
    this.checkBoxUseThisAddress = container.findElement(
        By.xpath("//input[@id='use_same_address']"));
    this.continueButton = container.findElement(
        By.xpath("//input[@name='submitAddress']"));
  }


}
