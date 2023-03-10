package framework.components;

import static framework.components.pages.BasePage.find;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class HeaderComponents {
  private static final By headerContainer = By.id("header");
  private final WebElement contactUsButton;
  private final WebElement languageButton;
  private final WebElement signInOutUserInfo;
  private final WebElement cartButton;
  private final WebElement logoMenuOption;
  private final WebElement clothesMenuOption;
  private final WebElement accessoriesMenuOption;
  private final WebElement artMenuOption;
  private final WebElement searchField;


  public HeaderComponents(WebElement container) {
    this.contactUsButton = container.findElement(By.xpath(".//*[@id='contact-link']"));
    this.languageButton = container.findElement(By.xpath(".//*[@id='_desktop_language_selector']"));
    this.signInOutUserInfo = container.findElement(By.xpath(".//*[@id='_desktop_user_info']"));
    this.cartButton = container.findElement(By.xpath(".//*[@id='_desktop_cart']"));
    this.logoMenuOption = container.findElement(By.xpath(".//*[@id='_desktop_logo']"));
    this.clothesMenuOption = container.findElement(By.xpath(".//*[@id='category-3']"));
    this.accessoriesMenuOption = container.findElement(By.xpath(".//*[@id='category-6']"));
    this.artMenuOption = container.findElement(By.xpath(".//*[@id='category-9']"));
    this.searchField = container.findElement(By.xpath(".//div[@id='search_widget']"));
  }


  public static HeaderComponents getHeaderComponents() {
    return new HeaderComponents(find(headerContainer));
  }

}
