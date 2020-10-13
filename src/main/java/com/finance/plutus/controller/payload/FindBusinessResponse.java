package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.BusinessDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 10/13/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindBusinessResponse {
  private BusinessDto business;
}
