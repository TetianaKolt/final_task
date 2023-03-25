package framework.pages;

import static framework.helpers.Helpers.getAllProducts;
import static framework.helpers.Helpers.scrollToElement;

import framework.components.ProductComponents;
import framework.enums.SortByOptions;
import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

  private final By sortButtonLocator =
      By.xpath("//button[@class='btn-unstyle select-title']");
  private final By productsContainerLocator =
      By.xpath("//div[@class='products row']/div");

  @Step("Choose 'Sort by' option")
  public HomePage chooseSortByOption(SortByOptions chooseOption) {
    scrollToElement(find(By.id("js-product-list-top")));
    find(sortButtonLocator).click();
    waitUntilVisible(chooseOption.getLocator(), 10);
    find(chooseOption.getLocator()).click();
    waitUntilSpinnerIsInvisible(10);
    return this;
  }

  @Step("Get product list")
  public List<ProductComponents> getProducts() {
    waitUntilPresent(By.xpath("//div[@class='products row']"), 10);
    scrollToElement(find(productsContainerLocator));
    return getAllProducts(productsContainerLocator);
  }
}
