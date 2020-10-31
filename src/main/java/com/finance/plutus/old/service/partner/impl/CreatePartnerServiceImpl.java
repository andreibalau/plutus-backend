package com.finance.plutus.old.service.partner.impl;

import com.finance.plutus.old.model.dto.CreatePartnerDto;
import com.finance.plutus.bank.model.Bank;
import com.finance.plutus.country.model.Country;
import com.finance.plutus.old.model.entity.Partner;
import com.finance.plutus.old.repository.PartnerRepository;
import com.finance.plutus.old.service.bank.FindBankService;
import com.finance.plutus.old.service.country.FindCountryService;
import com.finance.plutus.old.service.partner.CreatePartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class CreatePartnerServiceImpl implements CreatePartnerService {

  private final PartnerRepository partnerRepository;
  private final FindCountryService findCountryService;
  private final FindBankService findBankService;

  @Override
  @Transactional
  public UUID create(CreatePartnerDto createPartnerDto) {
    Partner partner = createPartner(createPartnerDto);
    partnerRepository.save(partner);
    return partner.getId();
  }

  private Partner createPartner(CreatePartnerDto createPartnerDto) {
    Bank bank =
        Optional.ofNullable(createPartnerDto.getBankId())
            .map(findBankService::findEntityById)
            .orElse(null);
    Country country = findCountryService.findEntityByCode(createPartnerDto.getCountryCode());
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
