package framework.helpers;

import com.github.javafaker.Faker;
import framework.pages.BasePage;
import io.qameta.allure.Attachment;
import java.io.File;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
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


  // Make a screenshot
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
