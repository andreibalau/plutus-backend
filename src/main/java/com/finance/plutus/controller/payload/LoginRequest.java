package com.finance.plutus.controller.payload;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Getter
@Setter
public class LoginRequest {
  @NotNull private String username;
  @NotNull private String password;
}
