package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.PreviewPartnerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindPartnersResponse {
  private List<PreviewPartnerDto> partners;
  private int page;
  private int size;
}
