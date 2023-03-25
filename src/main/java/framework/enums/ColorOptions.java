package framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
@AllArgsConstructor
public enum ColorOptions {
  WHITE("White", By.xpath("//label[@aria-label='White']/input")),
  BLACK("Black", By.xpath("//label[@aria-label='Black']/input"));

  private final String colorName;
  private final By locator;
}
