package framework.enums;

import lombok.Getter;

@Getter
public enum ColorOptions {
  WHITE("White"),
  BLACK("Black");

  private String colorName;

  ColorOptions(String colorName) {
    this.colorName = colorName;
  }
}
