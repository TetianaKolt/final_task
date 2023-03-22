package ui;

import framework.components.ProductComponents;
import framework.pages.MainPage;
import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class CheckPopularProductsSectionTest extends BaseTest {

  MainPage mainPage = new MainPage();

  @Test
  public void checkPopularProductsTest() {

    List<ProductComponents> productComponents = mainPage.getProductComponents();

    SoftAssertions softAssertions = new SoftAssertions();
    int expectedQuantityOfProducts = 8;

    //Check that 8 products exist in 'POPULAR PRODUCTS' section
    softAssertions.assertThat(productComponents.size())
        .as("Products quantity in 'POPULAR PRODUCTS' section is not as expected ["
            + expectedQuantityOfProducts + "]")
        .isEqualTo(expectedQuantityOfProducts);

    //Check that every product has name
    softAssertions.assertThat(productComponents)
        .map(ProductComponents::getProductTitleText)
        .as("Product(s) name is empty")
        .isNotNull();

    //Check that every product has price
    softAssertions.assertThat(productComponents)
        .map(ProductComponents::getProductNewPriceText)
        .as("Product(s) do(es) not have price")
        .isNotNull();

    //Check that all prices bigger than 0.00
    softAssertions.assertThat(productComponents)
        .map(ProductComponents::getProductNewPriceText)
        .as("Product(s) have(s) price(s) equal to 0.00")
        .allMatch(price -> price.compareTo(BigDecimal.ZERO) > 0);

    softAssertions.assertAll();
  }

}
