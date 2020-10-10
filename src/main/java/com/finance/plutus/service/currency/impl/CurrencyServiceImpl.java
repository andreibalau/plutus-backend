package com.finance.plutus.service.currency.impl;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.CurrencyRateDto;
import com.finance.plutus.model.entity.Currency;
import com.finance.plutus.model.entity.CurrencyRate;
import com.finance.plutus.repository.CurrencyRateRepository;
import com.finance.plutus.service.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Plutus Created by Catalin on 10/5/2020 */
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

  private final CurrencyRateRepository currencyRateRepository;

  @Override
  public CurrencyRate findLastRateByDate(LocalDate date, Currency currency) {
    return currencyRateRepository
        .findByDateLessThanEqualAndCurrency(date, currency)
        .orElseThrow(() -> new EntityNotFoundException("currency rate"));
  }

  @Override
  @Transactional
  public void create(CurrencyRateDto currencyRateDto) {
    CurrencyRate currencyRate =
        findCurrencyRate(currencyRateDto.getDate(), currencyRateDto.getCurrency())
            .orElse(createCurrencyRate(currencyRateDto));
    currencyRateRepository.save(currencyRate);
  }

  @Override
  @Transactional
  public void create(List<CurrencyRateDto> currencyRateDtoList) {
    currencyRateDtoList.forEach(this::create);
  }

  private Optional<CurrencyRate> findCurrencyRate(LocalDate date, Currency currency) {
    return currencyRateRepository.findByDateAndCurrency(date, currency);
  }

  private CurrencyRate createCurrencyRate(CurrencyRateDto currencyRateDto) {
    CurrencyRate currencyRate = new CurrencyRate();
    currencyRate.setCurrency(currencyRateDto.getCurrency());
    currencyRate.setDate(currencyRateDto.getDate());
    currencyRate.setRate(currencyRateDto.getRate());
    return currencyRate;
  }
}
