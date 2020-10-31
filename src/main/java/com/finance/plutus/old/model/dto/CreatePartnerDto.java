package com.finance.plutus.old.model.dto;

import com.finance.plutus.partner.model.BusinessType;
import com.finance.plutus.partner.model.PartnerType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class CreatePartnerDto {
  @NotBlank private String name;
  @NotNull private PartnerType type;
  @NotNull private BusinessType businessType;
  @Email private String email;
  @NotNull private String countryCode;
  private UUID bankId;
  private String phone;
  private String address;
  private String vat;
  private String bankAccount;
  private String commercialRegistry;
  private Integer termInDays;
}
