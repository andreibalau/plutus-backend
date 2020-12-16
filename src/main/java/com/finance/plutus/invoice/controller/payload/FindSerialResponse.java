package com.finance.plutus.invoice.controller.payload;

import com.finance.plutus.invoice.model.SerialDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 9/26/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindSerialResponse {
  private SerialDto serial;
}
