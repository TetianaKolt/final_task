package ui;

import static framework.pages.BasePage.waitUntilLoadingMessageDisappears;

import framework.browserFactory.BrowserFactory;
import framework.browserFactory.BrowserFactory.Browsers;
import framework.pages.BasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Log4j2
public class BaseTest {

  @BeforeMethod(alwaysRun = true)
  public synchronized void setUp() {
    int width = Integer.parseInt(System.getProperty("browser.width"));
    int height = Integer.parseInt(System.getProperty("browser.height"));
    String browser = System.getProperty("browser.type");

    log.info("Tests are run at {}x{}", height, width);

    WebDriver driver = BrowserFactory.getBrowser(Browsers.valueOf(browser));

    driver.get("https://demo.prestashop.com/");

//    driver.manage().window().setSize(new Dimension(height, width));
    driver.manage().window().maximize();
    BasePage.setDriverThreadLocal(driver);

    // Wait until loading message disappears and switch to iFrame of body
    waitUntilLoadingMessageDisappears();
  }

  @AfterMethod(alwaysRun = true)
  public void quit() {
    if (BasePage.getDriverThreadLocal() != null) {
      BasePage.getDriver().quit();
      BasePage.getDriverThreadLocal().remove();
    }
  }

}
