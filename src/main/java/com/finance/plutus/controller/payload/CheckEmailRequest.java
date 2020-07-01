package com.finance.plutus.controller.payload;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
public class CheckEmailRequest {
  @NotNull private String email;
}
