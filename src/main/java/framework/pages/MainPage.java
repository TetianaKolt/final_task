package framework.pages;

import static framework.enums.Categories.SubCategories.ACCESSORIES_SUB_CATEGORIES;
import static framework.enums.Categories.SubCategories.ART_SUB_CATEGORIES;
import static framework.enums.Categories.SubCategories.CLOTHES_SUB_CATEGORIES;
import static framework.helpers.Helpers.enterValueInSearchAndPressEnter;
import static framework.helpers.Helpers.getAllProducts;
import static framework.helpers.Helpers.hoverOverElement;
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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

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

  @Step("Go to the bottom of the page")
  public MainPage goToTheBottom() {
    log.info("Go to the bottom of the page");
    scrollToElement(find(footerLocator));
    return this;
  }

  @Step("Get Footer components")
  public FooterComponents getFooterComponents() {
    log.info("Get footer components");
    return new FooterComponents(find(footerLocator));
  }

  @Step("Get Header components")
  public HeaderComponents getHeaderComponents() {
    return new HeaderComponents(find(headerContainer));
  }

  /// @Step is moved to Helpers
  public List<ProductComponents> getProductComponents() {
    scrollToElement(find(By.xpath("//section[@class='featured-products clearfix']")));
    waitUntilPageContentIsLoaded(5);
    return getAllProducts(productComponentsLocator);
  }

  @Step("Get 'Block news' text near email")
  public String getTextNearEmailBlockNews() {
    log.info("Get 'Block news' text near email");
    return getFooterComponents().getTextNearEmailBlockNewsLetterText();
  }

  @Step("Get 'Unsubscribe at any moment' text under email")
  public String getTextUnderEmail() {
    log.info("Get 'Unsubscribe at any moment' text under email");
    return getFooterComponents().getTextUnderEmailInputText();
  }

  @Step("Check if text in 'SUBSCRIBE' button is uppercase")
  public boolean checkIfTextInSubscribeButtonIsUpperCase() {
    log.info("Check if text in 'SUBSCRIBE' button is uppercase");
    return getFooterComponents().getSubscribeButton().getCssValue("text-transform")
        .equals("uppercase");
  }

  @Step("Get list of languages")
  public List<String> getLanguageList() {
    getHeaderComponents().getLanguageButton().click();
    waitUntilVisible(languagesInDropdownLocator, 3);
    log.info("Get list of languages");
    return findAll(languagesInDropdownLocator).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  @Step("Check if language exists in the list")
  public boolean checkIfLanguageExistsInList(String languageToFind) {
    log.info("Check if language exists in the list");
    return getLanguageList().contains(languageToFind);
  }

  @Step("Click on sign in button")
  public LogInPage clickOnSignInButton() {
    log.info("Click on sign in button");
    getHeaderComponents().getSignInOutUserInfo().click();
    return new LogInPage();
  }

  @Step("Check name near the cart")
  public String checkNameNearCart() {
    waitUntilPageIsLoaded();
    return getHeaderComponents()
        .getSignInOutUserInfo()
        .findElement(nameNextToCartLocator)
        .getText();
  }

  @Step("Get all subcategories")
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

  @Step("Click 'Prices drop'")
  public PricesDropPage clickPricesDrop() {
    find(priceDropButtonLocator).click();
    waitUntilPageIsLoaded();
    return new PricesDropPage();
  }

  @Step("Click 'All products'")
  public HomePage clickAllProducts() {
    WebElement allProductsButton = find(allProductsButtonLocator);
    scrollToElement(allProductsButton);
    allProductsButton.click();
    return new HomePage();
  }

  @Step("Search product by text {wordToSearch}")
  public SearchResultsPage searchProductByText(String wordToSearch) {
    WebElement searchField = getHeaderComponents().getSearchField();
    enterValueInSearchAndPressEnter(searchField, wordToSearch);
    return new SearchResultsPage();
  }

}
