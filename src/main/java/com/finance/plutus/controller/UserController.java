package com.finance.plutus.controller;

import com.finance.plutus.controller.api.UserApi;
import com.finance.plutus.controller.payload.CheckEmailRequest;
import com.finance.plutus.controller.payload.CheckEmailResponse;
import com.finance.plutus.controller.payload.LoginRequest;
import com.finance.plutus.controller.payload.LoginResponse;
import com.finance.plutus.controller.payload.RegisterRequest;
import com.finance.plutus.model.dto.LoggedUserDto;
import com.finance.plutus.service.CheckEmailService;
import com.finance.plutus.service.LoginService;
import com.finance.plutus.service.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/** Plutus Created by catalin on 7/1/2020 */
@RestController
@RequiredArgsConstructor
public class UserController extends UserApi {

  private final CheckEmailService checkEmailService;
  private final LoginService loginService;
  private final RegisterUserService registerUserService;

  @Override
  public void register(RegisterRequest request) {
    registerUserService.register(request.getUser(), request.getBusiness());
  }

  @Override
  public CheckEmailResponse checkEmail(CheckEmailRequest request) {
    String email = request.getEmail();
    boolean exists = checkEmailService.exists(email);
    return new CheckEmailResponse(email, exists);
  }

  @Override
  public LoginResponse login(LoginRequest request) {
    LoggedUserDto user = loginService.login(request.getUsername(), request.getPassword());
    return new LoginResponse(user);
  }
}
