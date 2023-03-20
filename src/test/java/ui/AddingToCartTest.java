package ui;

import static framework.helpers.Helpers.checkTotalCalculation;

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

    CartModalWindowComponent cartModalWindow = mainPage.searchProductByText(
            wordToSearch)          //In the search field enter 'Bear' and press 'Enter'
        .clickOnProductWithName(
            productNameToClick)     //On the 'SEARCH RESULTS' page click on 'Brown Bear Notebook'
        .chooseProductType(productType)         //Change 'Paper type' to 'Doted'
        .changeQuantityTo(productQuantity)                           //Change 'Quantity' to '5'
        .clickAddToCart().getCartModalWindowComponents();//Click 'ADD TO CART' button

    SoftAssertions softAssertions = new SoftAssertions();
    //Check that new window with title 'Product successfully added to your shopping cart' appears
    String expectedModalWindowTitle = "Product successfully added to your shopping cart";

    softAssertions.assertThat(cartModalWindow.getModalTitle())
        .as("The [title] is not as expected")
        .isEqualTo(expectedModalWindowTitle);

    //Check that correct 'Paper Type' and 'Quantity' is shown on the left side of the window
    softAssertions.assertThat(cartModalWindow.getSelectedOptions())
        .as("Product [Option] is not according to the selected one: " + productType)
        .isEqualTo(productType);

    softAssertions.assertThat(cartModalWindow.getSelectedQuantityInt())
        .as("Product [quantity] is not according to the selected one: " + productQuantity)
        .isEqualTo(String.valueOf(productQuantity));

    //Check that 'Total' calculated correct
    BigDecimal expectedTotal = checkTotalCalculation(cartModalWindow.getProductPrice(),
        cartModalWindow.getSelectedQuantityInt(), cartModalWindow.getShippingValue());

    softAssertions.assertThat(cartModalWindow.getTotalValue())
        .as("Products [total] is not calculated correctly")
        .isEqualTo(expectedTotal);

    softAssertions.assertAll();
  }

}
