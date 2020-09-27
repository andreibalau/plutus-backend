package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.SerialDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Plutus Created by Catalin on 9/26/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindSerialsResponse {
  private List<SerialDto> serials;
}
