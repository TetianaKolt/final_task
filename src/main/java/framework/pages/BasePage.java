package framework.pages;

import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
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

  private static final By spinnerLocator = By.xpath(
      "//div[@class='faceted-overlay']//span[@class='spinner']");

  public static WebElement waitUntilVisible(By locator, int seconds) {
    return new WebDriverWait(getDriver(), seconds).until(
        ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public static WebElement waitUntilVisible(WebElement element, int seconds) {
    return new WebDriverWait(getDriver(), seconds).until(
        ExpectedConditions.visibilityOf(element));
  }

  public static Boolean waitUntilSpinnerIsInvisible(int seconds) {
    return new WebDriverWait(getDriver(), seconds).until(
        ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
  }

  public static WebElement waitUntilPresent(By locator, int seconds) {
    return new WebDriverWait(getDriver(), seconds).until(
        ExpectedConditions.presenceOfElementLocated(locator));
  }

  public static WebElement waitUntilClickable(WebElement element, int seconds) {
    return new WebDriverWait(getDriver(), seconds).until(
        ExpectedConditions.elementToBeClickable(element));
  }

  public static void waitUntilPageContentIsLoaded(int seconds) {
    WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
    wait.until(
        d -> Objects.equals(
            ((JavascriptExecutor) d).executeScript("return jQuery.active").toString(), "0"));
  }

  public static void waitUntilPageIsLoaded() {
    ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
        .equals("complete");
  }

  // wait until loading message disappears and switch to iFrame of body
  public static void waitUntilLoadingMessageDisappears() {
    WebDriverWait wait = new WebDriverWait(BasePage.getDriver(),5);
    WebElement loadingMessage = find(By.id("loadingMessage"));

    wait.until(ExpectedConditions.visibilityOfAllElements(loadingMessage));
    wait.until(ExpectedConditions.invisibilityOfAllElements(loadingMessage));
    getDriver().switchTo().frame("framelive");
  }
}
