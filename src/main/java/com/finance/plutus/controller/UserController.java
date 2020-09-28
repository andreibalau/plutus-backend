package com.finance.plutus.controller;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

import com.finance.plutus.controller.payload.ProfileUserResponse;
import com.finance.plutus.model.dto.UserDto;
import com.finance.plutus.service.user.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Plutus Created by catalin on 7/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final FindUserService findUserService;

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ProfileUserResponse getProfile() {
    UserDto userDto = findUserService.getProfile();
    return new ProfileUserResponse(userDto);
  }
}
