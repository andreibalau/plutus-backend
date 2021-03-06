package com.finance.plutus.currency;

import com.finance.plutus.currency.Currency;
import com.finance.plutus.currency.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/** Plutus Created by Catalin on 10/4/2020 */
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, UUID> {
  Optional<CurrencyRate> findByDateAndCurrency(LocalDate date, Currency currency);

  Optional<CurrencyRate> findTopByDateLessThanEqualAndCurrencyOrderByDateDesc(
      LocalDate date, Currency currency);
}
