package com.finance.plutus.model.dto;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
public class LoggedUserDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String token;
}
