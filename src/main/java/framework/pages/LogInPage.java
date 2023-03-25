package framework.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class LogInPage extends BasePage {

  private final By noAccountLinkLocator = By.xpath(
      "//a[@data-link-action='display-register-form']");

  @Step("Click on 'No account'")
  public CreateAnAccountPage clickOnNoAccountLink() {
    log.info("Click on 'No account'");
    find(noAccountLinkLocator).click();
    waitUntilPageIsLoaded();
    return new CreateAnAccountPage();
  }

}
