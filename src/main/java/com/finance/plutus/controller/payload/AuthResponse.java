package com.finance.plutus.controller.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Getter
@Setter
public class AuthResponse {
  @JsonAlias("access_token")
  private String accessToken;

  @JsonAlias("token_type")
  private String tokenType;

  @JsonAlias("refresh_token")
  private String refreshToken;

  @JsonAlias("expires_in")
  private Integer expiresIn;

  private String scope;
}
