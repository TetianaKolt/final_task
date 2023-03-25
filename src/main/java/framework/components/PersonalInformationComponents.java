package framework.components;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Getter
public class PersonalInformationComponents {

  private final WebElement socialTitleRadioButtonMR;
  private final WebElement socialTitleRadioButtonMS;
  private final WebElement firstNameInput;
  private final WebElement lastNameInput;
  private final WebElement emailInput;
  private final WebElement passwordInput;
  private final WebElement birthdateInput;
  private final WebElement receiveOffersCheckBox;
  private final WebElement customerDataPrivacyCheckBox;
  private final WebElement iAgreeCheckbox;
  private WebElement continueButton;
  public PersonalInformationComponents(WebElement container) {
    this.socialTitleRadioButtonMR = container.findElement(
        By.xpath("//*[@id='field-id_gender-1']"));
    this.socialTitleRadioButtonMS = container.findElement(
        By.xpath("//*[@id='field-id_gender-2']"));
    this.firstNameInput = container.findElement(By.xpath("//*[@id='field-firstname']"));
    this.lastNameInput = container.findElement(By.xpath("//*[@id='field-lastname']"));
    this.emailInput = container.findElement(By.xpath("//*[@id='field-email']"));
    this.passwordInput = container.findElement(By.xpath("//*[@id='field-password']"));
    this.birthdateInput = container.findElement(By.xpath("//*[@id='field-birthday']"));
    this.receiveOffersCheckBox = container.findElement(By.xpath(".//input[@name='optin']"));
    this.customerDataPrivacyCheckBox = container.findElement(
        By.xpath("//input[@name='customer_privacy']"));
    this.iAgreeCheckbox = container.findElement(By.xpath("//input[@name='psgdpr']"));
    try {
      this.continueButton = container.findElement(By.xpath(
          "//footer[@class='form-footer clearfix']"
              + "//button[@class='continue btn btn-primary float-xs-right']"));
    } catch (NoSuchElementException e) {
      this.continueButton = null;
    }
  }
}
