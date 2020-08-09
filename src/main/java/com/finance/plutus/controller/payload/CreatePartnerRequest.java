package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.CreateBusinessDto;
import com.finance.plutus.model.dto.CreatePartnerDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class CreatePartnerRequest {
  @Valid @NotNull private CreatePartnerDto partner;
  @Valid @NotNull private CreateBusinessDto business;
}