package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.Bank;
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
    bankDto.setId(bank.getId());
    bankDto.setName(bank.getName());
    return bankDto;
  }
}
