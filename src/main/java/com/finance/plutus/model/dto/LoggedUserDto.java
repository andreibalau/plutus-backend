package com.finance.plutus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Getter
@Setter
@AllArgsConstructor
public class LoggedUserDto {
  private UUID id;
  private String email;
  private String token;
}
