package com.finance.plutus.controller.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.dto.CreateSerialDto;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 9/7/2020 */
@Getter
@Setter
public class CreateSerialRequest {
  @Valid @NotNull private CreateSerialDto serial;
}
