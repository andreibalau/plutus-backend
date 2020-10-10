package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.ProfileUserResponse;
import com.finance.plutus.model.dto.UserDto;
import com.finance.plutus.service.user.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by catalin on 7/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final FindUserService findUserService;

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public ProfileUserResponse getProfile(@RequestParam String email) {
    UserDto userDto = findUserService.findDtoByEmail(email);
    return new ProfileUserResponse(userDto);
  }
}
