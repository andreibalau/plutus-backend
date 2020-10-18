package com.finance.plutus.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** Plutus Created by Catalin on 10/18/2020 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("keycloak")
public class KeycloakConfiguration {
  private String adminClientId;
  private String adminClientSecret;
  private String clientId;
  private String clientSecret;
  private String registerUri;
  private String loginUri;
}
