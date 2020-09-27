package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
@AllArgsConstructor
public class ProfileUserResponse {
  private UserDto profile;
}
