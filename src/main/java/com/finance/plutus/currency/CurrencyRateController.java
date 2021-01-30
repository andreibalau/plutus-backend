package com.finance.plutus.currency;

import com.finance.plutus.app.payload.PlutusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by Catalin on 1/25/2021 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rates")
public class CurrencyRateController {

  private final CurrencyRateFacadeService currencyRateFacadeService;

  @GetMapping(
      value = "/today",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public PlutusResponse<List<CurrencyRateDto>> fetchTodayRates() {
    return currencyRateFacadeService.fetchTodayRates();
  }
}
