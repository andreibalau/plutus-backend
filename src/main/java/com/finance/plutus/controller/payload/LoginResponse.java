package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.LoggedUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
  private LoggedUserDto user;
}
