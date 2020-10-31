package com.finance.plutus.old.controller;

import com.finance.plutus.old.controller.payload.RegisterRequest;
import com.finance.plutus.old.service.user.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.finance.plutus.old.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;

/** Plutus Created by catalin on 9/28/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class RegisterController {

  private final RegisterUserService registerUserService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void register(@Valid @RequestBody RegisterRequest request) {
    registerUserService.register(request.getUser());
  }
}
