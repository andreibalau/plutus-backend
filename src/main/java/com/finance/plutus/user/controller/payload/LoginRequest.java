package com.finance.plutus.user.controller.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Getter
@Setter
public class LoginRequest {
  @NotNull private String username;
  @NotNull private String password;
}
