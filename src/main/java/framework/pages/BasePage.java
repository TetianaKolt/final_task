package framework.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

  private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

  public static ThreadLocal<WebDriver> getDriverThreadLocal() {
    return DRIVER_THREAD_LOCAL;
  }

  public static void setDriverThreadLocal(WebDriver driver) {
    DRIVER_THREAD_LOCAL.set(driver);
  }

  public static WebDriver getDriver() {
    return DRIVER_THREAD_LOCAL.get();
  }

  public static List<WebElement> findAll(By locator) {
    return getDriver().findElements(locator);
  }

  public static WebElement find(By locator) {
    return getDriver().findElement(locator);
  }


  public void clickOnWebElement(WebElement webElement) {
    JavascriptExecutor executor = (JavascriptExecutor) getDriver();
    executor.executeScript("arguments[0].click()", webElement);
  }

  public void hoverOverElement(By locator) {
    WebElement element = find(locator);
    Actions action = new Actions(getDriver());
    action.moveToElement(element).build().perform();
  }

//  protected WebElement waitUntilClickable(By locator, int seconds) {
//    return new WebDriverWait(getDriver(), seconds).until(
//        ExpectedConditions.elementToBeClickable(locator));
//  }

  public static WebElement waitUntilVisible(By locator, int seconds) {
    return new WebDriverWait(getDriver(), seconds).until(
        ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public static Boolean waitUntilTextIsPresent(WebElement webElement, String text, int seconds) {
    return new WebDriverWait(getDriver(), seconds).until(
        ExpectedConditions.textToBePresentInElement(webElement, text));
  }

  public WebElement waitUntilPresent(By locator, int seconds) {
    return new WebDriverWait(getDriver(), seconds).until(
        ExpectedConditions.presenceOfElementLocated(locator));
  }

  public static void waitUntilPageIsLoaded() {
    ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
        .equals("complete");
  }


  // wait until loading message disappears and switch to iFrame of body
  public static void waitUntilLoadingMessageDisappears() {
    WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), 7000);
    WebElement loadingMessage = find(By.id("loadingMessage"));

    wait.until(ExpectedConditions.visibilityOfAllElements(loadingMessage));
    wait.until(ExpectedConditions.invisibilityOfAllElements(loadingMessage));
    getDriver().switchTo().frame("framelive");
  }


}
