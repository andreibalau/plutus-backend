package com.finance.plutus.old.service.currency;

import com.finance.plutus.old.model.dto.CurrencyRateDto;
import com.finance.plutus.old.model.entity.Currency;
import com.finance.plutus.old.model.entity.CurrencyRate;

import java.time.LocalDate;
import java.util.List;

/** Plutus Created by Catalin on 10/4/2020 */
public interface CurrencyService {

  CurrencyRate findLastRateByDate(LocalDate date, Currency currency);

  void create(CurrencyRateDto currencyRateDto);

  void create(List<CurrencyRateDto> currencyRateDtoList);
}
