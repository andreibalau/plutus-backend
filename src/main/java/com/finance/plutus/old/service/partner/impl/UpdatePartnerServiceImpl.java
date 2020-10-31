package com.finance.plutus.old.service.partner.impl;

import com.finance.plutus.old.model.dto.UpdatePartnerDto;
import com.finance.plutus.bank.model.Bank;
import com.finance.plutus.country.model.Country;
import com.finance.plutus.old.model.entity.Partner;
import com.finance.plutus.old.repository.PartnerRepository;
import com.finance.plutus.old.service.bank.FindBankService;
import com.finance.plutus.old.service.country.FindCountryService;
import com.finance.plutus.old.service.partner.FindPartnerService;
import com.finance.plutus.old.service.partner.UpdatePartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

/** Plutus Created by Catalin on 8/8/2020 */
@Service
@RequiredArgsConstructor
public class UpdatePartnerServiceImpl implements UpdatePartnerService {

  private final FindPartnerService findPartnerService;
  private final PartnerRepository partnerRepository;
  private final FindCountryService findCountryService;
  private final FindBankService findBankService;

  @Override
  @Transactional
  public void update(UUID id, UpdatePartnerDto updatePartnerDto) {
    Partner partner = findPartnerService.findEntityById(id);
    updatePartner(partner, updatePartnerDto);
    partnerRepository.save(partner);
  }

  private void updatePartner(Partner partner, UpdatePartnerDto updatePartnerDto) {
    Bank bank =
        Optional.ofNullable(updatePartnerDto.getBankId())
            .map(findBankService::findEntityById)
            .orElse(null);
    Country country = findCountryService.findEntityByCode(updatePartnerDto.getCountryCode());
    partner.setCountry(country);
    partner.setBank(bank);
    partner.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
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
