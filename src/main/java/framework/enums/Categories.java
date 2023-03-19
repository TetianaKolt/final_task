package framework.enums;

import lombok.Getter;

@Getter
public enum Categories {
  CLOTHES("//*[@id='category-3']"),
  ACCESSORIES("//*[@id='category-6']"),
  ART("//*[@id='category-9']");

  private final String locator;


  Categories(String locator) {
    this.locator = locator;
  }
}
