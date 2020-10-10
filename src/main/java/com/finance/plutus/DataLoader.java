package com.finance.plutus;

import com.finance.plutus.model.entity.Bank;
import com.finance.plutus.model.entity.Country;
import com.finance.plutus.model.entity.Item;
import com.finance.plutus.model.entity.ItemType;
import com.finance.plutus.model.entity.ItemVat;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.model.entity.PartnerType;
import com.finance.plutus.model.entity.Serial;
import com.finance.plutus.repository.BankRepository;
import com.finance.plutus.repository.CountryRepository;
import com.finance.plutus.repository.ItemRepository;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/** Plutus Created by Catalin on 10/4/2020 */
@Component
@RequiredArgsConstructor
public class DataLoader {

  private final CountryRepository countryRepository;
  private final BankRepository bankRepository;
  private final SerialRepository serialRepository;
  private final ItemRepository itemRepository;
  private final PartnerRepository partnerRepository;

  @PostConstruct
  public void load() {
    Country country = new Country();
    country.setCode("RO");
    country.setName("Romania");
    country.setCreatedOn(LocalDateTime.now());
    country.setUpdatedOn(LocalDateTime.now());
    countryRepository.save(country);

    Bank bank = new Bank();
    bank.setName("Banca");
    bank.setCreatedOn(LocalDateTime.now());
    bank.setUpdatedOn(LocalDateTime.now());
    bankRepository.save(bank);

    Serial serial = new Serial();
    serial.setCreatedOn(LocalDateTime.now());
    serial.setUpdatedOn(LocalDateTime.now());
    serial.setName("MC");
    serial.setCurrentNumber(0);
    serial.setNextNumber(1);
    serial.setStartNumber(1);
    serialRepository.save(serial);

    Item item = new Item();
    item.setCreatedOn(LocalDateTime.now());
    item.setUpdatedOn(LocalDateTime.now());
    item.setName("Test");
    item.setType(ItemType.PRODUCT);
    item.setVat(ItemVat.ZERO);
    item.setUnitPrice(1.00);
    item.setTotalPrice(1.00);
    itemRepository.save(item);

    Partner partner = new Partner();
    partner.setCreatedOn(LocalDateTime.now());
    partner.setUpdatedOn(LocalDateTime.now());
    partner.setName("Test");
    partner.setType(PartnerType.CLIENT);
    partner.setCountry(country);
    partnerRepository.save(partner);
  }
}
