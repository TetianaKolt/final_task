package framework.pages;

import static framework.helpers.Helpers.getAllProducts;
import static framework.helpers.Helpers.scrollToElement;

import framework.components.HeaderComponents;
import framework.components.ProductComponents;
import framework.enums.Categories;
import io.qameta.allure.Step;
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
  private final By textNearEmail = By.id("block-newsletter-label");
  private final By textUnderEmail = By.xpath("//div[@class='col-xs-12']/p");
  private final By subscribeButton = By.xpath("//input[@value='Subscribe']");
  private final By nameNextToCartLocator = By.xpath(
      "//a[@class='account']//*[@class='hidden-sm-down']");
  private final By headerContainer = By.id("header");
  private final By clothesSubcategoryLocator = By.xpath(
      " //div[@id='top_sub_menu_45451']//ul[@class='top-menu']/li");
  private final By accessoriesSubcategoryLocator = By.xpath(
      "//div[@id='top_sub_menu_48259']//ul[@class='top-menu']/li");

  private final By productComponentsLocator = By.xpath("//div[@class='product-description']");
  private final By priceDropButtonLocator = By.id("link-product-page-prices-drop-1");
  private final By allProductsButtonLocator = By.xpath("//a[contains(text(),'All products')]");


  public MainPage goToTheBottom() {
    scrollToElement(find(footerLocator));
    return this;
  }

  public HeaderComponents getHeaderComponents() {
    return new HeaderComponents(find(headerContainer));
  }

//  public List<ProductComponents> getProductComponents() {
//    List<ProductComponents> productComponents = new ArrayList<>();
//    List<WebElement> containers = findAll(productComponentsLocator);
//    for (WebElement container : containers) {
//      ProductComponents components = new ProductComponents(container);
//      productComponents.add(components);
//    }
//    return productComponents;
//  }

  public List<ProductComponents> getProductComponents() {
    scrollToElement(find(By.xpath("//section[@class='featured-products clearfix']")));
/// ????????????????????????????
    waitUntilVisible(By.xpath("//div[@class='thumbnail-top']"), 5);
    return getAllProducts(productComponentsLocator);
  }

  @Step
  public String getTextNearEmail() {
    return find(textNearEmail).getText();
  }

  @Step
  public String getTextUnderEmail() {
    return find(textUnderEmail).getText();
  }

  public boolean checkTextInSubscribeButton() {
    return find(subscribeButton).getCssValue("text-transform").equals("uppercase");
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

  public List<String> getAllCategories(Categories category) {
    hoverOverElement(By.xpath(category.getLocator()));

//    WebElement element = find(By.xpath(Categories.CLOTHES.getLocator()));
//
//    element.getText();
//    List<WebElement> subcategories = element.findElements(By.xpath("//li[@id='category-3']//li/a"));
//    subcategories.stream().map(WebElement::getText).collect(Collectors.toList());
//
//    List<WebElement> elements = findAll(By.cssSelector("ul.top-menu > li > a"));

//    JavascriptExecutor js = (JavascriptExecutor) getDriver();
//    // String innerText = js.executeScript(" return document.documentElement.innerText;").toString();
//    String innerText = js.executeScript("return document.querySelector('ul.top-menu').innerText;").toString();
//    List<String> menuTexts = new ArrayList<String>();
//    String[] menuItems = innerText.split("\\n");
//    for (String item : menuItems) {
//      if (item.equals("MEN") || item.equals("WOMEN")) {
//        menuTexts.add(item);
//      }
//    }
    switch (category) {
      case CLOTHES:
        return findAll(clothesSubcategoryLocator).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

      case ACCESSORIES:
        return findAll(accessoriesSubcategoryLocator).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }
    return null;
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
    WebElement searchField  = getHeaderComponents().getSearchField();
    Actions actions = new Actions(getDriver());
    actions.moveToElement(searchField)
        .click().sendKeys(wordToSearch)
        .sendKeys(Keys.ENTER)
        .build()
        .perform();
    return new SearchResultsPage();
  }

}
