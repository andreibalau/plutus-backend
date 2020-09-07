package com.finance.plutus.model.pdf;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
