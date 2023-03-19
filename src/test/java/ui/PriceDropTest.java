package ui;

import static framework.helpers.Helpers.checkCalculationOfDiscountedPrice;

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
    //At the bottom of the page click on 'Prices drop' link
    List<ProductComponents> products = mainPage.goToTheFooter()
        .clickPricesDrop()
        .getProducts();

    SoftAssertions softAssertions = new SoftAssertions();
    //Check that every product has old and new price
    softAssertions.assertThat(products)
        .map(ProductComponents::getProductRegularPrice)
        .as("Product(s) do(es) not have [old price]")
        .doesNotContainNull();

    softAssertions.assertThat(products)
        .map(ProductComponents::getProductPrice)
        .as("Product(s) do(es) not have [new price]")
        .doesNotContainNull();

    //Check that promo price for every product calculates correct
    List<BigDecimal> expectedPricesAfterCalculation = checkCalculationOfDiscountedPrice(products);

    softAssertions.assertThat(products)
        .map(ProductComponents::getProductPriceText)
        .as("Promo [price] is not calculated correctly")
        .containsExactlyElementsOf(expectedPricesAfterCalculation);

    softAssertions.assertAll();

  }

}
