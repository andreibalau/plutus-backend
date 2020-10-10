package com.finance.plutus.repository;

import com.finance.plutus.model.entity.Currency;
import com.finance.plutus.model.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

/** Plutus Created by Catalin on 10/4/2020 */
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, String> {
  Optional<CurrencyRate> findByDateAndCurrency(LocalDate date, Currency currency);

  Optional<CurrencyRate> findByDateLessThanEqualAndCurrency(LocalDate date, Currency currency);
}
