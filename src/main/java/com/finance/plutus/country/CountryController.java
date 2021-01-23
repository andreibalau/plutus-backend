package com.finance.plutus.country;

import com.finance.plutus.app.payload.PlutusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by Catalin on 10/4/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/countries")
public class CountryController {

  private final CountryFacadeService countryFacadeService;

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public PlutusResponse<List<CountryDto>> findAll() {
    return countryFacadeService.findAll();
  }
}
