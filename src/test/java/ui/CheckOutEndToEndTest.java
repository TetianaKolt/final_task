package ui;

import static framework.enums.ColorOptions.BLACK;
import static framework.helpers.FakeStringsHelper.generateFakeCity;
import static framework.helpers.FakeStringsHelper.generateFakeDate;
import static framework.helpers.FakeStringsHelper.generateFakeEmail;
import static framework.helpers.FakeStringsHelper.generateFakeFirstName;
import static framework.helpers.FakeStringsHelper.generateFakeLastName;
import static framework.helpers.FakeStringsHelper.generateFakePostalCode;
import static framework.helpers.FakeStringsHelper.generateFakeStreetAddress;
import static framework.helpers.Helpers.addSubtotalToShippingFee;

import framework.pages.CartPage;
import framework.pages.MainPage;
import framework.pages.OrderConfirmationPage;
import framework.pages.PersonalInformationPage;
import framework.pages.ProductPage;
import java.math.BigDecimal;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class CheckOutEndToEndTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void checkOutTest() {

    String productToSearch = "Mug";
    String customizableProduct = "Customizable Mug";
    String phraseToCustomize = "Best mug ever";
    String anotherProductToSearch = "T-Shirt";
    String anotherProductToClick = "Hummingbird Printed T-Shirt";

    ProductPage product = mainPage
        .searchProductByText(productToSearch)
        .clickOnProductWithName(customizableProduct);

    CartPage cart = product.customizeProduct(phraseToCustomize)
        .changeQuantityTo(1)
        .clickAddToCart()
        .clickContinueShopping()
        .findAnotherProductByText(anotherProductToSearch)
        .clickOnProductWithName(anotherProductToClick)
        .selectColor(BLACK)
        .clickAddToCart()
        .clickProceedToCheckout();

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(cart.getCartComponents().getTotalSum())
        .as("Calculation is not correct")
        .isEqualTo(cart.calculateTotal());

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

    BigDecimal expectedAmount = addSubtotalToShippingFee(
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
    BigDecimal expectedTotalPrice = addSubtotalToShippingFee(
        confirmationPage.getSubTotal(), confirmationPage.getShippingAndHandlingPrice());

    softAssertions.assertThat(actualTotalPrice)
        .as("Total is not calculated correctly")
        .isEqualTo(expectedTotalPrice);

    softAssertions.assertAll();
  }

}
