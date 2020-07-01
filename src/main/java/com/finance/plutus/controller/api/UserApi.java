package com.finance.plutus.controller.api;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import com.finance.plutus.controller.payload.CheckEmailRequest;
import com.finance.plutus.controller.payload.CheckEmailResponse;
import com.finance.plutus.controller.payload.LoginRequest;
import com.finance.plutus.controller.payload.LoginResponse;
import com.finance.plutus.controller.payload.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Plutus Created by catalin on 7/1/2020 */
@RequestMapping("/api/v1/users")
public abstract class UserApi extends BaseApi {

  @ResponseStatus(CREATED)
  @PostMapping(
      value = "/new",
      consumes = APPLICATION_PLUTUS_VALUE,
      produces = APPLICATION_PLUTUS_VALUE)
  public abstract void register(@Valid @RequestBody RegisterRequest request);

  @PostMapping(
      value = "/email",
      consumes = APPLICATION_PLUTUS_VALUE,
      produces = APPLICATION_PLUTUS_VALUE)
  public abstract CheckEmailResponse checkEmail(@Valid @RequestBody CheckEmailRequest request);

  @PostMapping(
      value = "/",
      consumes = APPLICATION_PLUTUS_VALUE,
      produces = APPLICATION_PLUTUS_VALUE)
  public abstract LoginResponse login(@Valid @RequestBody LoginRequest request);
}
