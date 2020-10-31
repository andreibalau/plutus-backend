package com.finance.plutus.old.authorization.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Plutus Created by Catalin on 10/17/2020 */
@Getter
@Setter
@ToString
public class UserCredentials {
  private boolean temporary = false;
  private String type = "password";
  private String value;

  public UserCredentials(String password) {
    this.value = password;
  }
}
