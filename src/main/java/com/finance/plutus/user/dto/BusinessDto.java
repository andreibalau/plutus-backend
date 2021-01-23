package com.finance.plutus.user.dto;

import com.finance.plutus.bank.BankDto;
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
}
