package com.finance.plutus.service.partner.impl;

import com.finance.plutus.model.dto.UpdatePartnerDto;
import com.finance.plutus.model.entity.Bank;
import com.finance.plutus.model.entity.Country;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.bank.FindBankService;
import com.finance.plutus.service.country.FindCountryService;
import com.finance.plutus.service.partner.PartnerEmailService;
import com.finance.plutus.service.partner.FindPartnerService;
import com.finance.plutus.service.partner.UpdatePartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by Catalin on 8/8/2020 */
@Service
@RequiredArgsConstructor
public class UpdatePartnerServiceImpl implements UpdatePartnerService {

  private final FindPartnerService findPartnerService;
  private final PartnerRepository partnerRepository;
  private final PartnerEmailService partnerEmailService;
  private final FindCountryService findCountryService;
  private final FindBankService findBankService;

  @Override
  @Transactional
  public void update(String id, UpdatePartnerDto updatePartnerDto) {
    Partner partner = findPartnerService.findEntityById(id);
    partnerEmailService.checkEmailExistence(updatePartnerDto.getEmail());
    updatePartner(partner, updatePartnerDto);
    partnerRepository.save(partner);
  }

  private void updatePartner(Partner partner, UpdatePartnerDto updatePartnerDto) {
    Bank bank = findBankService.findEntityById(updatePartnerDto.getBankId()).orElse(null);
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
