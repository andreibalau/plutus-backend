package com.finance.plutus.service.partner.impl;

import com.finance.plutus.model.dto.CreatePartnerDto;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.country.FindCountryService;
import com.finance.plutus.service.partner.CreatePartnerService;
import com.finance.plutus.service.user.CheckEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class CreatePartnerServiceImpl implements CreatePartnerService {

  private final PartnerRepository partnerRepository;
  private final CheckEmailService checkEmailService;
  private final FindCountryService findCountryService;

  @Override
  @Transactional
  public String create(CreatePartnerDto createPartnerDto) {
    checkEmailService.checkPartnerEmailExistence(createPartnerDto.getEmail());
    Partner partner = createPartner(createPartnerDto);
    partnerRepository.save(partner);
    return partner.getId();
  }

  private Partner createPartner(CreatePartnerDto createPartnerDto) {
    Partner partner = new Partner();
    partner.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    partner.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    partner.setEmail(createPartnerDto.getEmail());
    partner.setPhone(createPartnerDto.getPhone());
    partner.setType(createPartnerDto.getType());
    partner.setAddress(createPartnerDto.getAddress());
    partner.setVat(createPartnerDto.getVat());
    partner.setBank(null);
    partner.setBankAccount(createPartnerDto.getBankAccount());
    partner.setBusinessType(createPartnerDto.getBusinessType());
    partner.setCommercialRegistry(createPartnerDto.getCommercialRegistry());
    partner.setTermInDays(createPartnerDto.getTermInDays());
    partner.setCountry(findCountryService.findEntityByCode(createPartnerDto.getCountryCode()));
    partner.setName(createPartnerDto.getName());
    return partner;
  }
}
