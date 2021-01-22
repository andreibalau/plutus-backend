package com.finance.plutus.bank.infrastructure.adapters.primary;

import com.finance.plutus.bank.infrastructure.adapters.secondary.Bank;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 9/27/2020 */
@Getter
@Setter
public class BankDto {
  private String id;
  private String name;

  public static BankDto mapFromEntity(Bank bank) {
    BankDto bankDto = new BankDto();
    bankDto.setId(bank.getId().toString());
    bankDto.setName(bank.getName());
    return bankDto;
  }
}
