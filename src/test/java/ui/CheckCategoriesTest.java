package ui;

import static framework.enums.Categories.ACCESSORIES;
import static framework.enums.Categories.ART;
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
        mainPage.getAllSubCategories(CLOTHES);
    List<String> expectedSubNames = Arrays.asList("MEN", "WOMEN");

//    Check that 'MEN' and 'WOMEN' sub menu items appears
    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualSubNames)
        .as("Subcategories are not the same as expected in " + CLOTHES)
        .containsExactlyElementsOf(expectedSubNames);

//    Hover mouse over 'ACCESSORIES'
//    Check that 'STATIONERY' and 'HOME ACCESSORIES' sub menu items appears
    actualSubNames =
        mainPage.getAllSubCategories(ACCESSORIES);
    expectedSubNames = Arrays.asList("STATIONERY", "HOME ACCESSORIES");

//    Check that 'MEN' and 'WOMEN' sub menu items appears
    softAssertions.assertThat(actualSubNames)
        .as("Subcategories are not the same as expected in " + ACCESSORIES)
        .containsExactlyElementsOf(expectedSubNames);

    //Hover mouse over 'ART'
    actualSubNames = mainPage.getAllSubCategories(ART);

    //Check that no any sub category appears
    softAssertions.assertThat(actualSubNames)
        .as("Subcategories are not the same as expected in " + ART)
        .isEmpty();

    softAssertions.assertAll();
  }
}
