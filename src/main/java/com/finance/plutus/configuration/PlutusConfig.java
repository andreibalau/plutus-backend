package com.finance.plutus.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

/** Plutus Created by catalin on 7/1/2020 */
@Configuration
public class PlutusConfig {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new PasswordEncoder() {
      @Override
      public String encode(CharSequence charSequence) {
        return Base64.getEncoder().encodeToString(charSequence.toString().getBytes());
      }

      @Override
      public boolean matches(CharSequence charSequence, String s) {
        return Base64.getEncoder().encodeToString(charSequence.toString().getBytes()).equals(s);
      }
    };
  }
}
