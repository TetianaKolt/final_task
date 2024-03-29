package framework.pages;

import static framework.helpers.Helpers.getAllProducts;

import framework.components.ProductComponents;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;

public class SearchResultsPage extends BasePage {

  private final By productsContainerLocator = By.xpath(
      "//div[@class='products row']/div");

  /// @Step is moved to Helpers
  public List<ProductComponents> getProductComponents() {
    waitUntilPresent(productsContainerLocator, 10);
    return getAllProducts(productsContainerLocator);
  }

  @Step("Click on product with name {title}")
  public ProductPage clickOnProductWithName(String title) {
    List<ProductComponents> collect = getProductComponents().stream()
        .filter(pr -> pr.getProductTitleText().contains(title)).collect(Collectors.toList());
    collect.get(0).getProductTitleElement().click();
    return new ProductPage();
  }

}
