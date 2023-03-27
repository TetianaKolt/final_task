package ui;

import static framework.enums.Categories.ACCESSORIES;
import static framework.enums.Categories.ART;
import static framework.enums.Categories.CLOTHES;
import static framework.enums.Categories.SubCategories.ACCESSORIES_SUB_CATEGORIES;
import static framework.enums.Categories.SubCategories.CLOTHES_SUB_CATEGORIES;

import framework.pages.MainPage;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class CheckCategoriesTest extends BaseTest {

  MainPage mainPage = new MainPage();

  @Test
  public void checkCategoriesTest() {
    List<String> actualSubNames =
        mainPage.getAllSubCategories(CLOTHES);
    List<String> expectedSubNames = CLOTHES_SUB_CATEGORIES.getSubCategoriesNames();

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualSubNames)
        .as("Subcategories are not the same as expected in " + CLOTHES)
        .containsExactlyElementsOf(expectedSubNames);

    actualSubNames =
        mainPage.getAllSubCategories(ACCESSORIES);
    expectedSubNames = ACCESSORIES_SUB_CATEGORIES.getSubCategoriesNames();

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
