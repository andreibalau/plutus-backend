package com.finance.plutus.controller;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

import javax.validation.Valid;

import com.finance.plutus.controller.payload.LoginRequest;
import com.finance.plutus.controller.payload.LoginResponse;
import com.finance.plutus.model.dto.LoggedUserDto;
import com.finance.plutus.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Plutus Created by catalin.matache on 10/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

  private final LoginService loginService;

  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public LoginResponse login(@Valid @RequestBody LoginRequest request) {
    LoggedUserDto user = loginService.login(request.getUsername(), request.getPassword());
    return new LoginResponse(user);
  }
}
