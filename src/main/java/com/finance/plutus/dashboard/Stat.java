package com.finance.plutus.dashboard;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 1/14/2021 */
@Getter
@Setter
public class Stat {
  private String text;
  private Double value;

  public static Stat of(String text, Double value) {
    Stat stat = new Stat();
    stat.setText(text);
    stat.setValue(value);
    return stat;
  }
}
