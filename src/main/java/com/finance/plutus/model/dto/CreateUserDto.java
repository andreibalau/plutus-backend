package com.finance.plutus.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/** Plutus Created by Catalin on 7/1/2020 */
@Getter
@Setter
public class CreateUserDto {
  @Email @NotBlank private String email;
  @NotBlank private String password;
}
