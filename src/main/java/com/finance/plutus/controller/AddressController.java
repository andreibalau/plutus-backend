package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.FindCountiesResponse;
import com.finance.plutus.service.user.FindCountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by Catalin on 7/29/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AddressController {

  private final FindCountyService findCountyService;

  @GetMapping(
      value = "/counties",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindCountiesResponse findAllCounties() {
    return new FindCountiesResponse(findCountyService.findAll());
  }
}
