package com.finance.plutus.authorization.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/** Plutus Created by Catalin on 10/17/2020 */
@Getter
@Setter
@ToString
public class RegisterRequest {

  private boolean emailVerified = true;
  private boolean enabled = true;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private List<UserCredentials> credentials;
}
