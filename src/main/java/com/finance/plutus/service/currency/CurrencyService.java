package com.finance.plutus.service.currency;

import com.finance.plutus.model.dto.CurrencyRateDto;
import com.finance.plutus.model.entity.Currency;
import com.finance.plutus.model.entity.CurrencyRate;

import java.time.LocalDate;
import java.util.List;

/** Plutus Created by Catalin on 10/4/2020 */
public interface CurrencyService {

  CurrencyRate findLastRateByDate(LocalDate date, Currency currency);

  void create(CurrencyRateDto currencyRateDto);

  void create(List<CurrencyRateDto> currencyRateDtoList);
}
