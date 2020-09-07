package com.finance.plutus.controller.payload;

import java.util.List;

import com.finance.plutus.model.dto.PreviewCountyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Plutus Created by Catalin on 7/29/2020 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindCountiesResponse {
  private List<PreviewCountyDto> counties;
}
