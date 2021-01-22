package com.finance.plutus.partner.service.impl;

import com.finance.plutus.bank.infrastructure.adapters.secondary.Bank;
import com.finance.plutus.bank.core.application.BankFinder;
import com.finance.plutus.country.model.Country;
import com.finance.plutus.country.service.CountryFinder;
import com.finance.plutus.partner.model.CreatePartnerDto;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.repository.PartnerRepository;
import com.finance.plutus.partner.service.PartnerCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class PartnerCreatorImpl implements PartnerCreator {

  private final BankFinder bankFinder;
  private final CountryFinder countryFinder;
  private final PartnerRepository partnerRepository;

  @Override
  @Transactional
  public UUID create(CreatePartnerDto createPartnerDto) {
    Partner partner = createPartner(createPartnerDto);
    partnerRepository.save(partner);
    return partner.getId();
  }

  private Partner createPartner(CreatePartnerDto createPartnerDto) {
    Bank bank =
        Optional.ofNullable(createPartnerDto.getBankId()).map(bankFinder::findById).orElse(null);
    Country country = countryFinder.findByCode(createPartnerDto.getCountryCode());
    Partner partner = new Partner();
    partner.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    partner.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    partner.setEmail(createPartnerDto.getEmail());
    partner.setPhone(createPartnerDto.getPhone());
    partner.setType(createPartnerDto.getType());
    partner.setAddress(createPartnerDto.getAddress());
    partner.setVat(createPartnerDto.getVat());
    partner.setBankAccount(createPartnerDto.getBankAccount());
    partner.setBusinessType(createPartnerDto.getBusinessType());
    partner.setCommercialRegistry(createPartnerDto.getCommercialRegistry());
    partner.setTermInDays(createPartnerDto.getTermInDays());
    partner.setName(createPartnerDto.getName());
    partner.setBank(bank);
    partner.setCountry(country);
    return partner;
  }
}
