package com.finance.plutus.controller.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.dto.UpdateBusinessDto;
import com.finance.plutus.model.dto.UpdatePartnerDto;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 8/8/2020 */
@Getter
@Setter
public class UpdatePartnerRequest {
  @Valid @NotNull private UpdatePartnerDto partner;
  @Valid @NotNull private UpdateBusinessDto business;
}
