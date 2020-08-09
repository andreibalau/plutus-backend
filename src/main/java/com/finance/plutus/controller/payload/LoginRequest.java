package com.finance.plutus.controller.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
public class LoginRequest {
  @NotNull private String username;
  @NotNull private String password;
}