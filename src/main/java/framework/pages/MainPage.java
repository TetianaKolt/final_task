package framework.pages;

import static framework.helpers.Helpers.scrollToElement;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class MainPage extends BasePage{

  private final By footerLocator = By.xpath("//footer[@class='js-footer']");
  private final By textNearEmail = By.id("block-newsletter-label");
  private final By textUnderEmail = By.xpath("//div[@class='col-xs-12']/p");
  private final By subscribeButton = By.xpath("//input[@value='Subscribe']");


  public MainPage goToTheBottom() {
    scrollToElement(find(footerLocator));
    return this;
  }

  public String getTextNearEmail(By locator) {
    return find(locator).getText();
  }

  public boolean checkTextInSubscribeButton(){
    return find(subscribeButton).getCssValue("text-transform").equals("uppercase");
  }
}
