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
    List<String> actualSubNames =
        mainPage.getAllSubCategories(CLOTHES);
    List<String> expectedSubNames = Arrays.asList("MEN", "WOMEN");

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualSubNames)
        .as("Subcategories are not the same as expected in " + CLOTHES)
        .containsExactlyElementsOf(expectedSubNames);

    actualSubNames =
        mainPage.getAllSubCategories(ACCESSORIES);
    expectedSubNames = Arrays.asList("STATIONERY", "HOME ACCESSORIES");

    softAssertions.assertThat(actualSubNames)
        .as("Subcategories are not the same as expected in " + ACCESSORIES)
        .containsExactlyElementsOf(expectedSubNames);

    actualSubNames = mainPage.getAllSubCategories(ART);

    softAssertions.assertThat(actualSubNames)
        .as("Subcategories are not the same as expected in " + ART)
        .isEmpty();

    softAssertions.assertAll();
  }
}
