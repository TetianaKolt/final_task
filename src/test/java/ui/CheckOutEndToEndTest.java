package ui;

import static framework.enums.ColorOptions.BLACK;
import static framework.helpers.FakeStringsHelper.generateFakeCity;
import static framework.helpers.FakeStringsHelper.generateFakeDate;
import static framework.helpers.FakeStringsHelper.generateFakeEmail;
import static framework.helpers.FakeStringsHelper.generateFakeFirstName;
import static framework.helpers.FakeStringsHelper.generateFakeLastName;
import static framework.helpers.FakeStringsHelper.generateFakePostalCode;
import static framework.helpers.FakeStringsHelper.generateFakeStreetAddress;
import static framework.helpers.Helpers.checkTotalCalculationSubtotalShippingFee;

import framework.pages.CartPage;
import framework.pages.MainPage;
import framework.pages.OrderConfirmationPage;
import framework.pages.PersonalInformationPage;
import framework.pages.ProductPage;
import java.math.BigDecimal;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class CheckOutEndToEndTest extends BaseTest {

  private MainPage mainPage = new MainPage();

  @Test
  public void checkOutTest() {

    String productToSearch = "Mug";
    String customizableProduct = "Customizable Mug";
    String phraseToCustomize = "Best mug ever";
    String anotherProductToSearch = "T-Shirt";
    String anotherProductToClick = "Hummingbird Printed T-Shirt";

    //In the search field enter 'Mug' and press 'Enter'
    //On the 'SEARCH RESULTS' page click on 'Customizable Mug'

    ProductPage product = mainPage.searchProductByText(productToSearch)
        .clickOnProductWithName(customizableProduct);
    //Enter 'Best mug ever' in 'Product customization' field
    //Click 'SAVE CUSTOMIZATION'
    //Change 'Quantity' to '1' if not '1' already
    //Click 'ADD TO CART' button
    //On the next window click 'CONTINUE SHOPPING'

    CartPage cart = product.customizeProduct(phraseToCustomize)
        .changeQuantityTo(1)
        .clickAddToCart()
        .clickContinueShopping()
        .findAnotherProductByText(anotherProductToSearch)
        .clickOnProductWithName(anotherProductToClick)
        .selectColor(BLACK)
        .clickAddToCart()
        .clickProceedToCheckout();

    //In the search field enter 'T-Shirt' and press 'Enter'
    //On the 'SEARCH RESULTS' page click on 'Hummingbird Printed T-Shirt'
    //Select 'Black' color
    //Click 'ADD TO CART' button
    //On the next window click 'PROCEED TO CHECKOUT'

    //On the 'SHOPPING CART' page check that 'Total' calculated correct

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(cart.getCartComponents().getTotalSum())
        .as("Calculation is not correct")
        .isEqualTo(cart.checkTotalIsCorrect());

    //Click 'PROCEED TO CHECKOUT'

    String userFirstName = generateFakeFirstName();
    String userLastName = generateFakeLastName();
    String userEmail = generateFakeEmail();
    String userBirthDate = generateFakeDate();

    PersonalInformationPage personalInformation =
        cart.clickProceedToCheckoutToPersonalInfo()
            .fillFirstName(userFirstName)
            .fillLastName(userLastName)
            .fillEmail(userEmail)
            .fillBirthDate(userBirthDate)
            .tickCustomerDataPrivacyCheckbox()
            .tickIAgreeCheckbox()
            .clickContinueToAddress()
            .fillInAddress(generateFakeStreetAddress(), generateFakePostalCode(),
                generateFakeCity())
            .clickContinueToShippingMethod()
            .chooseRadioButtonMyCarrier()
            .clickContinueToPayment();

    BigDecimal actualAmount = personalInformation.selectPayByCheck();

    BigDecimal expectedAmount = checkTotalCalculationSubtotalShippingFee(
        personalInformation.getPersonalInfoPayment().getSubTotalSum(),
        personalInformation.getPersonalInfoPayment().getShippingSum());

    softAssertions.assertThat(actualAmount)
        .as("Actual amount is not calculated correctly")
        .isEqualTo(expectedAmount);

    OrderConfirmationPage confirmationPage = personalInformation
        .clickIAgreeCheckBox()
        .clickOnPlaceOrder();

    String actualTitle = confirmationPage.getTitleName();
    String expectedTitle = "YOUR ORDER IS CONFIRMED";

    softAssertions.assertThat(actualTitle)
        .as("Title is not as expected")
        .isEqualTo(expectedTitle);

    BigDecimal actualTotalPrice = confirmationPage.getTotalTaxIncl();
    BigDecimal expectedTotalPrice = checkTotalCalculationSubtotalShippingFee(
        confirmationPage.getSubTotal(), confirmationPage.getShippingAndHandlingPrice());

    softAssertions.assertThat(actualTotalPrice)
        .as("Total is not calculated correctly")
        .isEqualTo(expectedTotalPrice);

    //Fill 'PERSONAL INFORMATION' form with valid data (without password)
    //Check all necessary checkboxes
    //Click 'CONTINUE'
    //Fill the 'ADDRESSES' form with valid data
    //Click 'CONTINUE'
    //On the 'SHIPPING METHOD' section select 'My carrier'
    //Click 'CONTINUE'
    //On the 'PAYMENT' section select 'Pay by Check'
    //Check that Amount equal Subtotal+Shipping
    //Click on 'I agree..' checkbox
    //Click on 'Order with an obligation to pay'
    //Check that 'YOUR ORDER IS CONFIRMED' appeared on the next page
    //Check that 'TOTAL' calculated correct
    softAssertions.assertAll();
  }

}
