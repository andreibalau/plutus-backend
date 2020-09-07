package com.finance.plutus.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.entity.BusinessType;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 8/8/2020 */
@Getter
@Setter
public class UpdateBusinessDto {
  @NotNull private BusinessType type;
  @NotBlank private String name;
  @NotBlank private String cui;
  @NotBlank private String iban;
  private String regCom;
  @Valid @NotNull private UpdateAddressDto address;
}
