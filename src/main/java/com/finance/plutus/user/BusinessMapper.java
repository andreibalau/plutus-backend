package com.finance.plutus.user;

import com.finance.plutus.bank.BankMapper;
import com.finance.plutus.user.dto.BusinessDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
@RequiredArgsConstructor
public class BusinessMapper {

  private final BankMapper bankMapper;

  public BusinessDto mapToDto(Business business) {
    BusinessDto businessDto = new BusinessDto();
    businessDto.setName(business.getName());
    businessDto.setEmail(business.getEmail());
    businessDto.setPhone(business.getPhone());
    businessDto.setAddress(business.getAddress());
    businessDto.setVat(business.getVat());
    businessDto.setVies(business.getVies());
    businessDto.setWebsite(business.getWebsite());
    businessDto.setCommercialRegistry(business.getCommercialRegistry());
    businessDto.setBank(bankMapper.mapToDto(business.getBank()));
    businessDto.setBankAccount(business.getBankAccount());
    return businessDto;
  }
}
