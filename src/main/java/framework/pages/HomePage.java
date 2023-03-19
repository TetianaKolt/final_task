package framework.pages;

import static framework.helpers.Helpers.getAllProducts;
import static framework.helpers.Helpers.scrollToElement;

import framework.components.ProductComponents;
import framework.enums.SortByOptions;
import java.util.List;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

  private final By sortButtonLocator =
      By.xpath("//button[@class='btn-unstyle select-title']");
  private final By productsContainerLocator =
      By.xpath("//div[@class='js-product product col-xs-12 col-sm-6 col-xl-4']");

  public HomePage chooseSortByOption(SortByOptions chooseOption) {
    scrollToElement(find(By.id("js-product-list-top")));
    find(sortButtonLocator).click();
    find(By.xpath("//a[contains(text(),'"+chooseOption.getOptionText()+"')]")).click();
    return new HomePage();
  }

  public List<ProductComponents> getProducts() {
    waitUntilPageIsLoaded();
    scrollToElement(find(productsContainerLocator));
    return getAllProducts(productsContainerLocator);
  }
}
