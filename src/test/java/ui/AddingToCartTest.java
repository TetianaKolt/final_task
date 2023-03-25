package ui;

import static framework.helpers.Helpers.multiplyPriceByQuantityAddShipping;

import framework.components.CartModalWindowComponent;
import framework.pages.MainPage;
import java.math.BigDecimal;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class AddingToCartTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void checkCartTest() {

    String wordToSearch = "Bear";
    String productNameToClick = "Brown Bear Notebook";
    String productType = "Doted";
    int productQuantity = 5;

    CartModalWindowComponent cartModalWindow = mainPage
        .searchProductByText(wordToSearch)
        .clickOnProductWithName(productNameToClick)
        .chooseProductType(productType)
        .changeQuantityTo(productQuantity)
        .clickAddToCart().getCartModalWindowComponents();

    SoftAssertions softAssertions = new SoftAssertions();
    String expectedModalWindowTitle = "Product successfully added to your shopping cart";

    softAssertions.assertThat(cartModalWindow.getModalTitle())
        .as("The [title] is not as expected")
        .isEqualTo(expectedModalWindowTitle);

    softAssertions.assertThat(cartModalWindow.getSelectedOptions())
        .as("Product [Option] is not according to the selected one: " + productType)
        .isEqualTo(productType);

    softAssertions.assertThat(cartModalWindow.getSelectedQuantityInt())
        .as("Product [quantity] is not according to the selected one: " + productQuantity)
        .isEqualTo(String.valueOf(productQuantity));

    BigDecimal expectedTotal = multiplyPriceByQuantityAddShipping(cartModalWindow.getProductPrice(),
        cartModalWindow.getSelectedQuantityInt(), cartModalWindow.getShippingValue());

    softAssertions.assertThat(cartModalWindow.getTotalSum())
        .as("Products [total] is not calculated correctly")
        .isEqualTo(expectedTotal);

    softAssertions.assertAll();
  }

}
