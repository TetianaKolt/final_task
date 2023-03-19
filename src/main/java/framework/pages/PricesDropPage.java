package framework.pages;

import static framework.helpers.Helpers.getAllProducts;

import framework.components.ProductComponents;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;

public class PricesDropPage extends BasePage {

  private final By productsContainerLocator =
      By.xpath("//div[@class='js-product product col-xs-12 col-sm-6 col-xl-4']");

  public List<ProductComponents> getProducts() {
    waitUntilPageIsLoaded();
//    getDriver().switchTo().frame(find(By.id("framelive")));
    return getAllProducts(productsContainerLocator);
  }

  //Calculate if discount is reduced correctly
//  public BigDecimal checkCalculatedPrice(BigDecimal discount, BigDecimal regularPrice) {
//    return regularPrice.subtract(regularPrice.multiply(discount)
//        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN));
//  }
//
//  public List<BigDecimal> checkCalculations() {
//    List<BigDecimal> regularPrices = getProducts().stream()
//        .map(ProductComponents::getProductRegularPriceText).collect(Collectors.toList());
//
//    List<BigDecimal> discounts = getProducts().stream()
//        .map(ProductComponents::getProductDiscountTagText).collect(Collectors.toList());
//    List<BigDecimal> discountedPrices = new ArrayList<>();
//
//    for (int i = 0; i < regularPrices.size(); i++) {
//      discountedPrices.add(checkCalculatedPrice(discounts.get(i), regularPrices.get(i)))
//    }
//    return discountedPrices;
//  }


}
