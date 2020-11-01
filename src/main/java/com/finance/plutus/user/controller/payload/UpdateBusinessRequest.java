package com.finance.plutus.user.controller.payload;

import com.finance.plutus.user.model.BusinessDto;
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
