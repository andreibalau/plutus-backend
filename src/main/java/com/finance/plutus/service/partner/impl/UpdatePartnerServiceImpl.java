package com.finance.plutus.service.partner.impl;

import com.finance.plutus.model.dto.UpdatePartnerDto;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.country.FindCountryService;
import com.finance.plutus.service.partner.FindPartnerService;
import com.finance.plutus.service.partner.UpdatePartnerService;
import com.finance.plutus.service.user.CheckEmailService;
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
  private final CheckEmailService checkEmailService;
  private final FindCountryService findCountryService;

  @Override
  @Transactional
  public void update(String id, UpdatePartnerDto updatePartnerDto) {
    Partner partner = findPartnerService.findEntityById(id);
    checkEmailService.checkPartnerEmailExistence(updatePartnerDto.getEmail());
    updatePartner(partner, updatePartnerDto);
  }

  private void updatePartner(Partner partner, UpdatePartnerDto updatePartnerDto) {
    partner.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    partner.setEmail(updatePartnerDto.getEmail());
    partner.setPhone(updatePartnerDto.getPhone());
    partner.setType(updatePartnerDto.getType());
    partner.setAddress(updatePartnerDto.getAddress());
    partner.setVat(updatePartnerDto.getVat());
    partner.setBank(null);
    partner.setBankAccount(updatePartnerDto.getBankAccount());
    partner.setBusinessType(updatePartnerDto.getBusinessType());
    partner.setCommercialRegistry(updatePartnerDto.getCommercialRegistry());
    partner.setTermInDays(updatePartnerDto.getTermInDays());
    partner.setCountry(findCountryService.findEntityByCode(updatePartnerDto.getCountryCode()));
    partner.setName(updatePartnerDto.getName());
    partnerRepository.save(partner);
  }
}
