package framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
@AllArgsConstructor
public enum SortByOptions {

  BEST_SELLERS("Best sellers", By.xpath("//a[contains(text(),'Best sellers')]")),
  RELEVANCE("Relevance", By.xpath("//a[contains(text(),'Relevance')]")),
  NAME_A_TO_Z("Name, A to Z", By.xpath("//a[contains(text(),'Name, A to Z')]")),
  NAME_Z_TO_A("Name, Z to A", By.xpath("//a[contains(text(),'Name, Z to A')]")),
  PRICE_LOW_TO_HIGH("Price, low to high", By.xpath("//a[contains(text(),'Price, low to high')]")),
  PRICE_HIGH_TO_LOW("Price, high to low", By.xpath("//a[contains(text(),'Price, high to low')]"));

  private final String optionText;
  private final By locator;

}
