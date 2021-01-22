package com.finance.plutus.user.model;

import com.finance.plutus.bank.infrastructure.adapters.primary.BankDto;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 10/13/2020 */
@Getter
@Setter
public class BusinessDto {
  private String name;
  private String vat;
  private String commercialRegistry;
  private String address;
  private String bankAccount;
  private BankDto bank;
  private String email;
  private String phone;
  private String website;
  private String vies;

  public static BusinessDto mapFromEntity(Business business) {
    BusinessDto businessDto = new BusinessDto();
    businessDto.setName(business.getName());
    businessDto.setEmail(business.getEmail());
    businessDto.setPhone(business.getPhone());
    businessDto.setAddress(business.getAddress());
    businessDto.setVat(business.getVat());
    businessDto.setVies(business.getVies());
    businessDto.setWebsite(business.getWebsite());
    businessDto.setCommercialRegistry(business.getCommercialRegistry());
    businessDto.setBank(BankDto.mapFromEntity(business.getBank()));
    businessDto.setBankAccount(business.getBankAccount());
    return businessDto;
  }
}
