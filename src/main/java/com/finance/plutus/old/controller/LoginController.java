package com.finance.plutus.old.controller;

import com.finance.plutus.old.controller.payload.LoginRequest;
import com.finance.plutus.old.controller.payload.LoginResponse;
import com.finance.plutus.old.model.dto.LoggedUserDto;
import com.finance.plutus.old.service.user.UserAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.finance.plutus.old.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by catalin.matache on 10/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

  private final UserAuthenticator userAuthenticator;

  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public LoginResponse login(@Valid @RequestBody LoginRequest request) {
    LoggedUserDto user = userAuthenticator.login(request.getUsername(), request.getPassword());
    return new LoginResponse(user);
  }
}
