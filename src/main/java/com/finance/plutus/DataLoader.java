package com.finance.plutus;

import com.finance.plutus.bank.model.Bank;
import com.finance.plutus.country.model.Country;
import com.finance.plutus.old.model.entity.Serial;
import com.finance.plutus.bank.repository.BankRepository;
import com.finance.plutus.country.repository.CountryRepository;
import com.finance.plutus.old.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/** Plutus Created by Catalin on 10/4/2020 */
@Component
@RequiredArgsConstructor
public class DataLoader {

  private final CountryRepository countryRepository;
  private final BankRepository bankRepository;
  private final SerialRepository serialRepository;

  @PostConstruct
  public void load() {
    loadCountries();
    loadBanks();
//    loadSerials();
  }

  private void loadCountries() {
    Country country = new Country();
    country.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    country.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    country.setCode("RO");
    country.setName("Romania");
    countryRepository.save(country);
    country.setCode("IT");
    country.setName("Italia");
    countryRepository.save(country);
    country.setCode("SG");
    country.setName("Singapore");
    countryRepository.save(country);
    country.setCode("DE");
    country.setName("Germania");
    countryRepository.save(country);
    country.setCode("SW");
    country.setName("Elvetia");
    countryRepository.save(country);
    country.setCode("SUA");
    country.setName("Statele Unite ale Americii");
    countryRepository.save(country);
  }

  private void loadBanks() {
    Bank bank = new Bank();
    bank.setId(UUID.fromString("3cd86ac8-d71f-40cf-bd7b-28f186f6fb28"));
    bank.setName("Banca Transilvania");
    bank.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    bank.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    bankRepository.save(bank);
  }

  private void loadSerials() {
    Serial serial = new Serial();
    serial.setId(UUID.fromString("2e978bc3-115d-4226-90a7-24bd24ef5054"));
    serial.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    serial.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    serial.setName("MC");
    serial.setCurrentNumber(0);
    serial.setNextNumber(1);
    serial.setStartNumber(1);
    serialRepository.save(serial);
  }
}
