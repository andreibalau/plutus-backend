package com.finance.plutus.partner.dto;

import com.finance.plutus.bank.BankDto;
import com.finance.plutus.country.CountryDto;
import com.finance.plutus.partner.BusinessType;
import com.finance.plutus.partner.PartnerType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
}
