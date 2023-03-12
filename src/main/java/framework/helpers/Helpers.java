package framework.helpers;

import static framework.pages.BasePage.find;

import com.github.javafaker.Faker;
import framework.pages.BasePage;
import io.qameta.allure.Attachment;
import java.io.File;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;


public class Helpers {


  // scroll to element
  public static void scrollToElement(WebElement element) {
    JavascriptExecutor jse = (JavascriptExecutor) BasePage.getDriver();
    jse.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  // check if highlighted in red
  public static boolean isHighlightedInRed(By locator, String cssValue){
    return find(locator).getCssValue(cssValue).equals("rgba(255, 76, 76, 1)");
  }

  // Take a screenshot
  @SneakyThrows
  public static void makeScreenShot() {
    File scrFile = ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile,
        new File("/Users/AIM/IdeaProjects/pageObjectLab/src/test/resources/screenshots"
            + new Faker().random().hex(10) + ".png"));
  }

  @Attachment(value = "{fileName}", type = "image/png")
  public static byte[] takeScreenShot(String fileName) {
    return ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.BYTES);
  }



}
