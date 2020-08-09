package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.BusinessType;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 8/8/2020 */
@Getter
@Setter
public class BusinessDto {
  private BusinessType type;
  private String name;
  private String cui;
  private String iban;
  private String regCom;
  private AddressDto address;
}
