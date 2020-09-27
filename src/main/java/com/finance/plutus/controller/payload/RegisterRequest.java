package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.CreateUserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
public class RegisterRequest {
  @Valid @NotNull private CreateUserDto user;
}
