package framework.pages;

import static framework.helpers.Helpers.getAllProducts;

import framework.components.ProductComponents;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;

public class SearchResultsPage extends BasePage {

  private final By productsContainerLocator = By.xpath(
      "//div[@class='products row']/div");


  public List<ProductComponents> getProductComponents() {
    waitUntilPresent(productsContainerLocator,10);
    return getAllProducts(productsContainerLocator);
  }

  public ProductPage clickOnProductWithName(String title) {
    List<ProductComponents> collect = getProductComponents()
        .stream()
        .filter(pr -> pr.getProductTitleText().contains(title))
        .collect(Collectors.toList());
    collect.get(0).getProductTitleElement().click();
    return new ProductPage();
  }

}
