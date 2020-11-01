package com.finance.plutus.user.authorization.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Plutus Created by Catalin on 10/17/2020 */
@Getter
@Setter
@ToString
public class AuthorizationResponse {
  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("refresh_token")
  private String refreshToken;

  @JsonProperty("expires_in")
  private Integer expiresIn;

  @JsonProperty("refresh_expires_in")
  private Integer refreshExpiresIn;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("not_before_policy")
  private Integer notBeforePolicy;

  @JsonProperty("session_state")
  private String sessionState;

  private String scope;
}
