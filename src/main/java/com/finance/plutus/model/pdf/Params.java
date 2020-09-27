package com.finance.plutus.model.pdf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/** Plutus Created by catalin on 9/7/2020 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Params {
  private String name;

  public Map<String, Object> getMap() {
    return Map.of("name", name);
  }
}
