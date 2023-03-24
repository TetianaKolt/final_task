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
    //Test case #8 (Sorting check)
    //
    //Go to the https://demo.prestashop.com/
    //Click on the 'All products >' under the 'POPULAR PRODUCTS' section
    //Sort products as 'Name, A to Z'
    HomePage homePage = mainPage.clickAllProducts();

    List<ProductComponents> products = homePage
        .chooseSortByOption(NAME_A_TO_Z)
        .getProducts();

    List<String> expectedTitlesOrder = products.stream()
        .map(ProductComponents::getProductTitleText)
        .sorted()
        .collect(Collectors.toList());

    //Check that sorting is correct
    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(products)
        .map(ProductComponents::getProductTitleText)
        .as("Products are not sorted correctly according to the option " + NAME_A_TO_Z)
        .containsExactlyElementsOf(expectedTitlesOrder);

    //Sort products as 'Name, Z to A'

    products = homePage
        .chooseSortByOption(NAME_Z_TO_A)
        .getProducts();

    expectedTitlesOrder = products.stream()
        .map(ProductComponents::getProductTitleText)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

    //Check that sorting is correct
    softAssertions.assertThat(products)
        .map(ProductComponents::getProductTitleText)
        .as("Products are not sorted correctly according to the option " + NAME_Z_TO_A)
        .containsExactlyElementsOf(expectedTitlesOrder);

    //Sort products as 'Price, low to high'
    products = homePage
        .chooseSortByOption(PRICE_LOW_TO_HIGH)
        .getProducts();

    List<BigDecimal> expectedPricesOrder = products.stream()
        .map(ProductComponents::getProductNewPriceText)
        .sorted()
        .collect(Collectors.toList());

    //Check that sorting is correct
    softAssertions.assertThat(products)
        .map(ProductComponents::getProductNewPriceText)
        .as("Products are not sorted correctly according to the option " + PRICE_LOW_TO_HIGH)
        .containsExactlyElementsOf(expectedPricesOrder);

    //Sort products as 'Price, high to low'
    products = homePage
        .chooseSortByOption(PRICE_HIGH_TO_LOW)
        .getProducts();

    expectedPricesOrder = products.stream()
        .map(ProductComponents::getProductNewPriceText)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

    //Check that sorting is correct
    softAssertions.assertThat(products)
        .map(ProductComponents::getProductNewPriceText)
        .as("Products are not sorted correctly according to the option " + PRICE_HIGH_TO_LOW)
        .containsExactlyElementsOf(expectedPricesOrder);

    softAssertions.assertAll();
  }

}
