package com.finance.plutus.model.dto;

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
  @NotBlank private String firstName;
  @NotBlank private String lastName;
  @Email @NotBlank private String email;
  @NotNull private PartnerType type;
  private String phone;
}
