package com.finance.plutus.invoice.controller.payload;

import com.finance.plutus.invoice.model.CreateSerialDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Plutus Created by catalin on 9/7/2020 */
@Getter
@Setter
public class CreateSerialRequest {
  @Valid @NotNull private CreateSerialDto serial;
}
