package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.BusinessType;
import com.finance.plutus.model.entity.PartnerType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/** Plutus Created by Catalin on 8/8/2020 */
@Getter
@Setter
public class UpdatePartnerDto {
  @NotBlank private String name;
  @NotNull private PartnerType type;
  @NotNull private BusinessType businessType;
  @Email private String email;
  @NotNull private String countryCode;
  private String bankId;
  private String phone;
  private String address;
  private String vat;
  private String bankAccount;
  private String commercialRegistry;
  private Integer termInDays;
}
