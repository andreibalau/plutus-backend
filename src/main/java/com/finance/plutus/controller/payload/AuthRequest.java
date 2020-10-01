package com.finance.plutus.controller.payload;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Getter
@Setter
public class AuthRequest {
  private String username;
  private String password;
  private String grantType = "password";

  public AuthRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
