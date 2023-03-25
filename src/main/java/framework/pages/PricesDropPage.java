package framework.pages;

import static framework.helpers.Helpers.getAllProducts;

import framework.components.ProductComponents;
import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.By;

public class PricesDropPage extends BasePage {

  private final By productsContainerLocator = By.xpath(
      "//div[@class='js-product product col-xs-12 col-sm-6 col-xl-4']");

  @Step("Get list of products")
  public List<ProductComponents> getProducts() {
    waitUntilPageIsLoaded();
    return getAllProducts(productsContainerLocator);
  }
}
