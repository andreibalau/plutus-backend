package com.finance.plutus.service.user.authorization;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Plutus Created by catalin.matache on 10/1/2020 */
@FeignClient(name = "register-user", url = "${security.oauth2.resource.user-register-uri}")
public interface RegisterClient {

  @PostMapping
  void register(
      @RequestParam String username, @RequestParam String password, @RequestParam String role);
}
