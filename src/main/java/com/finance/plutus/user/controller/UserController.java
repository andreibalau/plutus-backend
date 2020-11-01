package com.finance.plutus.user.controller;

import com.finance.plutus.user.controller.payload.FindBusinessResponse;
import com.finance.plutus.user.model.BusinessDto;
import com.finance.plutus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by catalin on 7/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  @GetMapping(
      path = "/{id}/business",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindBusinessResponse findBusiness(@PathVariable UUID id) {
    BusinessDto businessDto = userService.getBusiness(id);
    return new FindBusinessResponse(businessDto);
  }
}
