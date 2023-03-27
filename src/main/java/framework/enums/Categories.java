package framework.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Categories {
  CLOTHES("//*[@id='category-3']"),
  ACCESSORIES("//*[@id='category-6']"),
  ART("//*[@id='category-9']");

  private final String locator;

  @Getter
  @AllArgsConstructor
  public enum SubCategories {
    CLOTHES_SUB_CATEGORIES(
        "//li[@id='category-3']//a[@class='dropdown-item dropdown-submenu']",
        Arrays.asList("MEN", "WOMEN")),
    ACCESSORIES_SUB_CATEGORIES(
        "//li[@id='category-6']//a[@class='dropdown-item dropdown-submenu']",
        Arrays.asList("STATIONERY", "HOME ACCESSORIES")),
    ART_SUB_CATEGORIES(
        "//*[@id='category-9']//a[@class='dropdown-item dropdown-submenu']",
        Collections.emptyList());

    private final String subLocators;
    private final List<String> subCategoriesNames;
  }
}
