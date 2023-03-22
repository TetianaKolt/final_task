package framework.pages;

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
  private final By clothesSubcategoryLocator = By.xpath("//li[@id='category-3']"
      + "//a[@class='dropdown-item dropdown-submenu']");
  private final By accessoriesSubcategoryLocator = By.xpath("//li[@id='category-6']"
      + "//a[@class='dropdown-item dropdown-submenu']");

  private final By productComponentsLocator = By.xpath("//div[@class='product-description']");
  private final By priceDropButtonLocator = By.id("link-product-page-prices-drop-1");
  private final By allProductsButtonLocator = By.xpath("//a[contains(text(),'All products')]");


  public MainPage goToTheBottom() {
    scrollToElement(find(footerLocator));
    return this;
  }

  public FooterComponents getFooterComponents() {
    return new FooterComponents(find(footerLocator));
  }

  public HeaderComponents getHeaderComponents() {
    return new HeaderComponents(find(headerContainer));
  }

  public List<ProductComponents> getProductComponents() {
    scrollToElement(find(By.xpath("//section[@class='featured-products clearfix']")));
    waitUntilVisible(By.xpath("//div[@class='thumbnail-top']"), 5);
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

  public boolean checkTextInSubscribeButton() {
    return getFooterComponents().getSubscribeButton().getCssValue("text-transform")
        .equals("uppercase");
  }

  @Step
  public List<String> getLanguageList() {
    getHeaderComponents().getLanguageButton().click();
    return findAll(By.xpath("//*[@class='dropdown-item']")).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  @Step
  public boolean checkIfLanguageExistsInList(String languageToFind) {
    return getLanguageList().contains(languageToFind);
  }

  public LogInPage clickOnSignInButton() {
    getHeaderComponents().getSignInOutUserInfo().click();
    return new LogInPage();
  }

  public String checkNameNearCart() {
    waitUntilPageIsLoaded();
    return getHeaderComponents()
        .getSignInOutUserInfo()
        .findElement(nameNextToCartLocator)
        .getText();
  }

  public List<String> getAllSubCategories(Categories category) {
    hoverOverElement(By.xpath(category.getLocator()));
    getDriver().switchTo().activeElement();

    switch (category) {
      case CLOTHES:
        return findAll(clothesSubcategoryLocator).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

      case ACCESSORIES:
        return findAll(accessoriesSubcategoryLocator).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

      case ART:
        return new ArrayList<>();

      default:
        return null;
    }
  }

  public MainPage goToTheFooter() {
    scrollToElement(find(By.id("footer")));
    return this;
  }

  public PricesDropPage clickPricesDrop() {
    find(priceDropButtonLocator).click();
    return new PricesDropPage();
  }

  public HomePage clickAllProducts() {
    WebElement allProductsButton = find(allProductsButtonLocator);
    scrollToElement(allProductsButton);
    allProductsButton.click();
    return new HomePage();
  }

  public SearchResultsPage searchProductByText(String wordToSearch) {
    WebElement searchField = getHeaderComponents().getSearchField();
    Actions actions = new Actions(getDriver());
    actions.moveToElement(searchField)
        .click().sendKeys(wordToSearch)
        .sendKeys(Keys.ENTER)
        .build()
        .perform();
    return new SearchResultsPage();
  }

}
