package framework.pages;

import static framework.enums.Categories.SubCategories.ACCESSORIES_SUB_CATEGORIES;
import static framework.enums.Categories.SubCategories.ART_SUB_CATEGORIES;
import static framework.enums.Categories.SubCategories.CLOTHES_SUB_CATEGORIES;
import static framework.helpers.Helpers.enterValueInSearchAndPressEnter;
import static framework.helpers.Helpers.getAllProducts;
import static framework.helpers.Helpers.scrollToElement;

import framework.components.FooterComponents;
import framework.components.HeaderComponents;
import framework.components.ProductComponents;
import framework.enums.Categories;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Getter
@Log4j2
public class MainPage extends BasePage {

  private final By footerLocator = By.xpath("//footer[@class='js-footer']");

  private final By subscribeButton = By.xpath("//input[@value='Subscribe']");
  private final By nameNextToCartLocator = By.xpath(
      "//a[@class='account']//*[@class='hidden-sm-down']");
  private final By headerContainer = By.id("header");
  private final By languagesInDropdownLocator = By.xpath(
      "//*[@class='dropdown-item']");
  private final By productComponentsLocator = By.xpath(
      "//div[@class='product-description']");
  private final By priceDropButtonLocator = By.id("link-product-page-prices-drop-1");
  private final By allProductsButtonLocator = By.xpath(
      "//a[contains(text(),'All products')]");

  @Step
  public MainPage goToTheBottom() {
    scrollToElement(find(footerLocator));
    return this;
  }

  @Step
  public FooterComponents getFooterComponents() {
    return new FooterComponents(find(footerLocator));
  }

  @Step
  public HeaderComponents getHeaderComponents() {
    return new HeaderComponents(find(headerContainer));
  }

  @Step
  public List<ProductComponents> getProductComponents() {
    scrollToElement(find(By.xpath("//section[@class='featured-products clearfix']")));
    waitUntilPageContentIsLoaded(5);
    return getAllProducts(productComponentsLocator);
  }

  @Step
  public String getTextNearEmailBlockNews() {
    return getFooterComponents().getTextNearEmailBlockNewsLetterText();
  }

  @Step
  public String getTextUnderEmail() {
    return getFooterComponents().getTextUnderEmailInputText();
  }

  @Step
  public boolean checkIfTextInSubscribeButtonIsUpperCase() {
    return getFooterComponents().getSubscribeButton().getCssValue("text-transform")
        .equals("uppercase");
  }

  @Step
  public List<String> getLanguageList() {
    getHeaderComponents().getLanguageButton().click();
    waitUntilVisible(languagesInDropdownLocator, 3);
    return findAll(languagesInDropdownLocator).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  @Step
  public boolean checkIfLanguageExistsInList(String languageToFind) {
    return getLanguageList().contains(languageToFind);
  }

  @Step
  public LogInPage clickOnSignInButton() {
    getHeaderComponents().getSignInOutUserInfo().click();
    return new LogInPage();
  }

  @Step
  public String checkNameNearCart() {
    waitUntilPageIsLoaded();
    return getHeaderComponents()
        .getSignInOutUserInfo()
        .findElement(nameNextToCartLocator)
        .getText();
  }

  @Step
  public List<String> getAllSubCategories(Categories category) {
    hoverOverElement(By.xpath(category.getLocator()));
    getDriver().switchTo().activeElement();

    switch (category) {
      case CLOTHES:
        return findAll(By.xpath(CLOTHES_SUB_CATEGORIES.getSubLocators())).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

      case ACCESSORIES:
        return findAll(By.xpath(ACCESSORIES_SUB_CATEGORIES.getSubLocators())).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

      case ART:
        try {
          return findAll(By.xpath(ART_SUB_CATEGORIES.getSubLocators())).stream()
              .map(WebElement::getText)
              .collect(Collectors.toList());
        } catch (NoSuchElementException e) {
          return new ArrayList<>();
        }
      default:
        return null;
    }
  }

  @Step
  public MainPage goToTheFooter() {
    scrollToElement(find(By.id("footer")));
    return this;
  }

  @Step
  public PricesDropPage clickPricesDrop() {
    find(priceDropButtonLocator).click();
    waitUntilPageIsLoaded();
    return new PricesDropPage();
  }

  @Step
  public HomePage clickAllProducts() {
    WebElement allProductsButton = find(allProductsButtonLocator);
    scrollToElement(allProductsButton);
    allProductsButton.click();
    return new HomePage();
  }

  @Step
  public SearchResultsPage searchProductByText(String wordToSearch) {
    WebElement searchField = getHeaderComponents().getSearchField();
    enterValueInSearchAndPressEnter(searchField, wordToSearch);
    return new SearchResultsPage();
  }

}
