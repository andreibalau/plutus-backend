package com.finance.plutus.user.controller;

import com.finance.plutus.user.controller.payload.LoginRequest;
import com.finance.plutus.user.controller.payload.LoginResponse;
import com.finance.plutus.user.model.LoggedUserDto;
import com.finance.plutus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by catalin.matache on 10/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

  private final UserService userService;

  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public LoginResponse login(@Valid @RequestBody LoginRequest request) {
    LoggedUserDto user = userService.login(request.getUsername(), request.getPassword());
    return new LoginResponse(user);
  }
}
