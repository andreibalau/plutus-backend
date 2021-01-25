package com.finance.plutus.currency;

import com.finance.plutus.app.payload.PlutusResponse;

import java.util.List;

/** Plutus Created by Catalin on 1/25/2021 */
public interface CurrencyRateFacadeService {
  PlutusResponse<List<CurrencyRateDto>> fetchTodayRates();
}
