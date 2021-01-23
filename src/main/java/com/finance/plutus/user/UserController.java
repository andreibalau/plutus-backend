package com.finance.plutus.user;

import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.user.dto.BusinessDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by catalin on 7/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserFacadeService userFacadeService;

  @GetMapping(
      path = "/business",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public PlutusResponse<BusinessDto> findBusiness(@AuthenticationPrincipal Jwt jwt) {
    return userFacadeService.getBusiness(jwt);
  }
}
