package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.FindCountriesResponse;
import com.finance.plutus.model.dto.CountryDto;
import com.finance.plutus.service.country.FindCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by Catalin on 10/4/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/countries")
public class CountryController {

  private final FindCountryService findCountryService;

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindCountriesResponse findAll() {
    List<CountryDto> countries = findCountryService.findAllDto();
    return new FindCountriesResponse(countries);
  }
}