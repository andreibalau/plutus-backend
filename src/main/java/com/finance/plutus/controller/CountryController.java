package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.FindCountriesResponse;
import com.finance.plutus.model.dto.CountryDto;
import com.finance.plutus.service.country.FindCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Plutus Created by Catalin on 10/4/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/countries")
public class CountryController {

  private final FindCountryService findCountryService;

  @GetMapping
  public FindCountriesResponse findAll() {
    List<CountryDto> countries = findCountryService.findAllDto();
    return new FindCountriesResponse(countries);
  }
}
