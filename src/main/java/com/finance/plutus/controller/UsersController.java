package com.finance.plutus.controller;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import com.finance.plutus.controller.payload.CheckEmailRequest;
import com.finance.plutus.controller.payload.CheckEmailResponse;
import com.finance.plutus.controller.payload.LoginRequest;
import com.finance.plutus.controller.payload.LoginResponse;
import com.finance.plutus.controller.payload.ProfileUserResponse;
import com.finance.plutus.controller.payload.RegisterRequest;
import com.finance.plutus.model.dto.LoggedUserDto;
import com.finance.plutus.model.dto.UserProfileDto;
import com.finance.plutus.service.CheckEmailService;
import com.finance.plutus.service.FindUserService;
import com.finance.plutus.service.LoginService;
import com.finance.plutus.service.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Plutus Created by catalin on 7/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {

  private final CheckEmailService checkEmailService;
  private final LoginService loginService;
  private final RegisterUserService registerUserService;
  private final FindUserService findUserService;

  @ResponseStatus(CREATED)
  @PostMapping(
      value = "/new",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void register(@Valid @RequestBody RegisterRequest request) {
    registerUserService.register(request.getUser(), request.getBusiness());
  }

  @PostMapping(
      value = "/email",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public CheckEmailResponse checkEmail(@Valid @RequestBody CheckEmailRequest request) {
    String email = request.getEmail();
    boolean exists = checkEmailService.exists(email);
    return new CheckEmailResponse(email, exists);
  }

  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public LoginResponse login(@Valid @RequestBody LoginRequest request) {
    LoggedUserDto user = loginService.login(request.getUsername(), request.getPassword());
    return new LoginResponse(user);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ProfileUserResponse getProfile() {
    UserProfileDto userProfileDto = findUserService.getProfile();
    return new ProfileUserResponse(userProfileDto);
  }
}
