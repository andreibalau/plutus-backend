package com.finance.plutus.old.controller.payload;

import com.finance.plutus.old.model.dto.BusinessDto;
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
