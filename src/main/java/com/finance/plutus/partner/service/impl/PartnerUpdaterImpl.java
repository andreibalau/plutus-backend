package com.finance.plutus.partner.service.impl;

import com.finance.plutus.bank.infrastructure.adapters.secondary.Bank;
import com.finance.plutus.bank.core.application.BankFinder;
import com.finance.plutus.country.model.Country;
import com.finance.plutus.country.service.CountryFinder;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.model.UpdatePartnerDto;
import com.finance.plutus.partner.repository.PartnerRepository;
import com.finance.plutus.partner.service.PartnerFinder;
import com.finance.plutus.partner.service.PartnerUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class PartnerUpdaterImpl implements PartnerUpdater {

  private final BankFinder bankFinder;
  private final CountryFinder countryFinder;
  private final PartnerFinder partnerFinder;
  private final PartnerRepository partnerRepository;

  @Override
  @Transactional
  public void update(UUID id, UpdatePartnerDto updatePartnerDto) {
    Partner partner = partnerFinder.findById(id);
    updatePartner(partner, updatePartnerDto);
    partnerRepository.save(partner);
  }

  private void updatePartner(Partner partner, UpdatePartnerDto updatePartnerDto) {
    Bank bank =
        Optional.ofNullable(updatePartnerDto.getBankId()).map(bankFinder::findById).orElse(null);
    Country country = countryFinder.findByCode(updatePartnerDto.getCountryCode());
    partner.setCountry(country);
    partner.setBank(bank);
    partner.setEmail(updatePartnerDto.getEmail());
    partner.setPhone(updatePartnerDto.getPhone());
    partner.setType(updatePartnerDto.getType());
    partner.setAddress(updatePartnerDto.getAddress());
    partner.setVat(updatePartnerDto.getVat());
    partner.setBankAccount(updatePartnerDto.getBankAccount());
    partner.setBusinessType(updatePartnerDto.getBusinessType());
    partner.setCommercialRegistry(updatePartnerDto.getCommercialRegistry());
    partner.setTermInDays(updatePartnerDto.getTermInDays());
    partner.setName(updatePartnerDto.getName());
  }
}
