package ui;

import framework.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SubscribeTest extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
  public void subscribeAreaTest() {
    String actualTextNearEmailFieldInTheBottom = mainPage
        .goToTheBottom()
        .getTextNearEmailBlockNews();

    String expectedTextNearEmailFieldInTheBottom = "Get our latest news and special sales";

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualTextNearEmailFieldInTheBottom)
        .as("Actual text near the email field in the bottom differs from expected")
        .isEqualTo(expectedTextNearEmailFieldInTheBottom);

    String actualTextUnderEmailFieldInTheBottom = mainPage
        .goToTheBottom()
        .getTextUnderEmail();

    String expectedTextUnderEmailInTheBottom = "You may unsubscribe at any moment. "
        + "For that purpose, please find my contact info in the legal notice.";

    softAssertions.assertThat(actualTextUnderEmailFieldInTheBottom)
        .as("Actual text near the email field in the bottom differs from expected")
        .isEqualTo(expectedTextUnderEmailInTheBottom);

    boolean isSubscribeTextInUpperCase = mainPage
        .checkIfTextInSubscribeButtonIsUpperCase();

    softAssertions.assertThat(isSubscribeTextInUpperCase)
        .as("Text is not in uppercase " + isSubscribeTextInUpperCase)
        .isTrue();

    softAssertions.assertAll();
  }

}
