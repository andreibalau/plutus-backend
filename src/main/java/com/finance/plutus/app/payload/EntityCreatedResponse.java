package com.finance.plutus.app.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@AllArgsConstructor
public class EntityCreatedResponse {
  private UUID id;
}
