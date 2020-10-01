package com.finance.plutus.service.user.authorization;

import java.util.Map;

import com.finance.plutus.configuration.FormUrlEncodedClientConfiguration;
import com.finance.plutus.controller.payload.AuthResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/** Plutus Created by catalin.matache on 10/1/2020 */
@FeignClient(
    name = "login-user",
    url = "${security.oauth2.resource.user-login-uri}",
    configuration = FormUrlEncodedClientConfiguration.class)
public interface LoginClient {

  @Headers("Content-Type: application/x-www-form-urlencoded")
  @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  AuthResponse login(@RequestHeader String authorization, Map<String, ?> formParams);
}
