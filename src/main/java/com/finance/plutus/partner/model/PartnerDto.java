package com.finance.plutus.partner.model;

import com.finance.plutus.bank.infrastructure.adapters.primary.BankDto;
import com.finance.plutus.country.model.CountryDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class PartnerDto {
  private String id;
  private String name;
  private String email;
  private PartnerType type;
  private String phone;
  private String vat;
  private String commercialRegistry;
  private String address;
  private Integer termInDays;
  private String bankAccount;
  private BankDto bank;
  private CountryDto country;
  private BusinessType businessType;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;

  public static PartnerDto mapFromEntity(Partner partner) {
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
        Optional.ofNullable(partner.getBank()).map(BankDto::mapFromEntity).orElse(null));
    partnerDto.setCountry(CountryDto.mapFromEntity(partner.getCountry()));
    partnerDto.setBusinessType(partner.getBusinessType());
    return partnerDto;
  }
}
