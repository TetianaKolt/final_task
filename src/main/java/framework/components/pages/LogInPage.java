package framework.components.pages;

import org.openqa.selenium.By;

public class LogInPage extends BasePage{

  private final By noAccountLinkLocator = By.xpath("//a[@data-link-action='display-register-form']");


  public CreateAnAccountPage clickOnNoAccountLink() {
    find(noAccountLinkLocator).click();
    waitUntilPageIsLoaded();
    return new CreateAnAccountPage();
  }

}
