package ui;

import framework.pages.MainPage;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class LanguageTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void checkLanguages() {

    List<String> languages = mainPage.getLanguageList();
    int actualQuantityOfLanguages = languages.size();
    int expectedQuantityOfLanguages = 44;

    //Check that 44 languages exists in 'Language' dropdown in the top menu
    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualQuantityOfLanguages)
        .as("Actual quantity of languages differs from expected")
        .isEqualTo(expectedQuantityOfLanguages);

    //Check that 'Українська' language exist in dropdown
    boolean exists = mainPage.checkIfLanguageExistsInList("Укіраїнська");
    softAssertions.assertThat(exists)
        .as("The language is not in the list")
        .isTrue();

    softAssertions.assertAll();

  }

}