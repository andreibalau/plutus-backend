package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.BusinessDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Plutus Created by Catalin on 10/13/2020 */
@Getter
@Setter
public class UpdateBusinessRequest {
  @Valid @NotNull private BusinessDto business;
}
