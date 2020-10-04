package com.finance.plutus;

import com.finance.plutus.model.entity.Bank;
import com.finance.plutus.model.entity.Country;
import com.finance.plutus.repository.BankRepository;
import com.finance.plutus.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.UUID;

/** Plutus Created by Catalin on 10/4/2020 */
@Component
@RequiredArgsConstructor
public class DataLoader {

  private final CountryRepository countryRepository;
  private final BankRepository bankRepository;

  @PostConstruct
  public void load() {
    Country country = new Country();
    country.setCode("RO");
    country.setName("Romania");
    country.setCreatedOn(LocalDateTime.now());
    country.setUpdatedOn(LocalDateTime.now());
    countryRepository.save(country);

    Bank bank = new Bank();
    bank.setId(UUID.randomUUID().toString());
    bank.setName("Banca");
    bank.setCreatedOn(LocalDateTime.now());
    bank.setUpdatedOn(LocalDateTime.now());
    bankRepository.save(bank);
  }
}
