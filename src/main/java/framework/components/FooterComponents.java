package framework.components;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
@Getter
public class FooterComponents {

  private final WebElement textNearEmailBlockNewsLetter;
  private final String textNearEmailBlockNewsLetterText;

  private final WebElement textUnderEmailInput;
  private final String textUnderEmailInputText;

  private final WebElement subscribeButton;

  private final WebElement emailInput;

  public FooterComponents(WebElement footerContainer) {
    this.textNearEmailBlockNewsLetter = footerContainer.findElement(By.id("block-newsletter-label"));
    this.textNearEmailBlockNewsLetterText = textNearEmailBlockNewsLetter.getText();
    this.textUnderEmailInput = footerContainer.findElement(By.xpath("//div[@class='col-xs-12']/p"));
    this.textUnderEmailInputText = textUnderEmailInput.getText();
    this.subscribeButton = footerContainer.findElement(By.xpath("//input[@value='Subscribe']"));
    this.emailInput = footerContainer.findElement(By.xpath("//input[@name='email']"));
  }
}
