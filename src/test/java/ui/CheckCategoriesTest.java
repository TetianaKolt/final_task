package ui;


import static framework.enums.Categories.CLOTHES;

import framework.pages.MainPage;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class CheckCategoriesTest extends BaseTest {

  MainPage mainPage = new MainPage();

  @Test
  public void checkCategoriesTest() {
    //Hover mouse over 'CLOTHES'
    List<String> actualSubNames =
        mainPage.getAllCategories(CLOTHES);
    List<String> expectedSubNames = Arrays.asList("MEN", "WOMEN");

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualSubNames)
        .as("Subcategories are not the same as expected")
        .containsExactlyElementsOf(expectedSubNames);

//    Check that 'MEN' and 'WOMEN' sub menu items appears
//    Hover mouse over 'ACCESSORIES'
//    Check that 'STATIONERY' and 'HOME ACCESSORIES' sub menu items appears
    //Hover mouse over 'ART'
    //Check that no any sub category appears

    softAssertions.assertAll();
  }
}
