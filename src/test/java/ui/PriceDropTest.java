package ui;

import static framework.helpers.Helpers.calculateDiscountedPrice;

import framework.components.ProductComponents;
import framework.pages.MainPage;
import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class PriceDropTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void priceDropCheckTest() {
    List<ProductComponents> products = mainPage.goToTheBottom().clickPricesDrop().getProducts();

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(products).map(ProductComponents::getProductRegularPrice)
        .as("Product(s) do(es) not have [old price]").doesNotContainNull();

    softAssertions.assertThat(products).map(ProductComponents::getProductNewPrice)
        .as("Product(s) do(es) not have [new price]").doesNotContainNull();

    List<BigDecimal> expectedPricesAfterCalculation = calculateDiscountedPrice(products);

    softAssertions.assertThat(products).map(ProductComponents::getProductNewPriceText)
        .as("Promo [price] is not calculated correctly")
        .containsExactlyElementsOf(expectedPricesAfterCalculation);

    softAssertions.assertAll();
  }

}
