package com.finance.plutus.bank;

import org.springframework.stereotype.Component;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
public class BankMapper {

  public BankDto mapToDto(Bank bank) {
    BankDto bankDto = new BankDto();
    bankDto.setId(bank.getId().toString());
    bankDto.setName(bank.getName());
    return bankDto;
  }
}
