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

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualQuantityOfLanguages)
        .as("Actual quantity of languages differs from expected")
        .isEqualTo(expectedQuantityOfLanguages);

    String languageToLookFor = "Українська";
    softAssertions.assertThat(languages)
        .as("The language [" + languageToLookFor + "] is not in the list")
        .contains(languageToLookFor);
    softAssertions.assertAll();
  }

}
