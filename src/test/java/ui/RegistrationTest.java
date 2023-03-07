package ui;

import framework.pages.MainPage;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest{
  private final MainPage mainPage = new MainPage();

  @Test
  public void registrationWithValidDataTest() {
    mainPage.clickOnSignInButton();
  }

}
