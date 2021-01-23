package com.finance.plutus.partner;

import com.finance.plutus.bank.BankMapper;
import com.finance.plutus.country.CountryMapper;
import com.finance.plutus.partner.dto.CreatePartnerDto;
import com.finance.plutus.partner.dto.PartnerDto;
import com.finance.plutus.partner.dto.UpdatePartnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
@RequiredArgsConstructor
public class PartnerMapper {

  private final BankMapper bankMapper;
  private final CountryMapper countryMapper;

  public PartnerDto mapToDto(Partner partner) {
    PartnerDto partnerDto = new PartnerDto();
    partnerDto.setId(partner.getId().toString());
    partnerDto.setCreatedOn(partner.getCreatedOn());
    partnerDto.setUpdatedOn(partner.getUpdatedOn());
    partnerDto.setEmail(partner.getEmail());
    partnerDto.setPhone(partner.getPhone());
    partnerDto.setType(partner.getType());
    partnerDto.setName(partner.getName());
    partnerDto.setVat(partner.getVat());
    partnerDto.setCommercialRegistry(partner.getCommercialRegistry());
    partnerDto.setTermInDays(partner.getTermInDays());
    partnerDto.setBankAccount(partner.getBankAccount());
    partnerDto.setAddress(partner.getAddress());
    partnerDto.setBank(
        Optional.ofNullable(partner.getBank()).map(bankMapper::mapToDto).orElse(null));
    partnerDto.setCountry(countryMapper.mapToDto(partner.getCountry()));
    partnerDto.setBusinessType(partner.getBusinessType());
    return partnerDto;
  }

  public Partner mapToEntity(CreatePartnerDto createPartnerDto) {
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
    return partner;
  }

  public Partner mapToEntity(Partner partner, UpdatePartnerDto updatePartnerDto) {
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
    return partner;
  }
}
