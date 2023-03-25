package ui;

import static framework.enums.SortByOptions.NAME_A_TO_Z;
import static framework.enums.SortByOptions.NAME_Z_TO_A;
import static framework.enums.SortByOptions.PRICE_HIGH_TO_LOW;
import static framework.enums.SortByOptions.PRICE_LOW_TO_HIGH;

import framework.components.ProductComponents;
import framework.pages.HomePage;
import framework.pages.MainPage;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SortingTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void checkSortingTest() {

    HomePage homePage = mainPage.clickAllProducts();

    List<ProductComponents> products = homePage.chooseSortByOption(NAME_A_TO_Z).getProducts();

    List<String> expectedTitlesOrder = products.stream()
        .map(ProductComponents::getProductTitleText)
        .sorted()
        .collect(Collectors.toList());

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(products).map(ProductComponents::getProductTitleText)
        .as("Products aren't sorted correctly according to the option " + NAME_A_TO_Z)
        .containsExactlyElementsOf(expectedTitlesOrder);

    products = homePage
        .chooseSortByOption(NAME_Z_TO_A)
        .getProducts();

    expectedTitlesOrder = products.stream()
        .map(ProductComponents::getProductTitleText)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

    softAssertions.assertThat(products)
        .map(ProductComponents::getProductTitleText)
        .as("Products aren't sorted correctly according to the option " + NAME_Z_TO_A)
        .containsExactlyElementsOf(expectedTitlesOrder);

    products = homePage
        .chooseSortByOption(PRICE_LOW_TO_HIGH)
        .getProducts();

    List<BigDecimal> expectedPricesOrder = products.stream()
        .map(ProductComponents::getProductNewPriceText)
        .sorted()
        .collect(Collectors.toList());

    softAssertions.assertThat(products).map(ProductComponents::getProductNewPriceText)
        .as("Products aren't sorted correctly according to the option " + PRICE_LOW_TO_HIGH)
        .containsExactlyElementsOf(expectedPricesOrder);

    products = homePage
        .chooseSortByOption(PRICE_HIGH_TO_LOW)
        .getProducts();

    expectedPricesOrder = products.stream()
        .map(ProductComponents::getProductRegularPriceText)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

    softAssertions.assertThat(products).map(ProductComponents::getProductRegularPriceText)
        .as("Products aren't sorted correctly according to the option " + PRICE_HIGH_TO_LOW)
        .containsExactlyElementsOf(expectedPricesOrder);

    softAssertions.assertAll();
  }

}
