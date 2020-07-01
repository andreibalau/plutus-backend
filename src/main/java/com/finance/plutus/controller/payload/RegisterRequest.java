package com.finance.plutus.controller.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
public class RegisterRequest {

  @NotBlank private String firstName;
  @NotBlank private String lastName;
  @Email @NotBlank private String email;
  @NotBlank private String password;
  private String phone;
}
