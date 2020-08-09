package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.UpdateBusinessDto;
import com.finance.plutus.model.dto.UpdatePartnerDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Plutus Created by Catalin on 8/8/2020 */
@Getter
@Setter
public class UpdatePartnerRequest {
  @Valid @NotNull private UpdatePartnerDto partner;
  @Valid @NotNull private UpdateBusinessDto business;
}
