package framework.components.pages;

import static framework.components.HeaderComponents.getHeaderComponents;
import static framework.helpers.Helpers.scrollToElement;

import io.qameta.allure.Step;
import java.util.List;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@Getter
@Log4j2
public class MainPage extends BasePage {

  private final By footerLocator = By.xpath("//footer[@class='js-footer']");
  private final By textNearEmail = By.id("block-newsletter-label");
  private final By textUnderEmail = By.xpath("//div[@class='col-xs-12']/p");
  private final By subscribeButton = By.xpath("//input[@value='Subscribe']");
  private final By nameNextToCartLocator = By.xpath(
      "//a[@class='account']//*[@class='hidden-sm-down']");


  public MainPage goToTheBottom() {
    scrollToElement(find(footerLocator));
    return this;
  }

  @Step
  public String getTextNearEmail() {
    return find(textNearEmail).getText();
  }

  @Step
  public String getTextUnderEmail() {
    return find(textUnderEmail).getText();
  }

  public boolean checkTextInSubscribeButton() {
    return find(subscribeButton).getCssValue("text-transform").equals("uppercase");
  }

  @Step
  public List<WebElement> getLanguageList() {
    getHeaderComponents().getLanguageButton().click();
    Select select = new Select(find(By.xpath("//select[@class='link hidden-md-up']")));
    return select.getOptions();
  }

  @Step
  public boolean checkIfLanguageExistsInList(String languageISOCode) {
    for (WebElement lan : getLanguageList()) {
      if (lan.getAttribute("data-iso-code").equals(languageISOCode)) {
        return true;
      }
    }
    return false;
  }

  public LogInPage clickOnSignInButton() {
    getHeaderComponents().getSignInOutUserInfo().click();
    return new LogInPage();
  }

  public WebElement checkNameNearCart() {
    return getHeaderComponents()
        .getSignInOutUserInfo()
        .findElement(nameNextToCartLocator);
  }
}
