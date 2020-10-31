package com.finance.plutus.old.controller.payload;

import com.finance.plutus.old.model.dto.CreateUserDto;
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
