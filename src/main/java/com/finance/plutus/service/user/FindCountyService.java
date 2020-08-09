package com.finance.plutus.service.user;

import com.finance.plutus.model.dto.PreviewCountyDto;

import java.util.List;

/** Plutus Created by Catalin on 7/29/2020 */
public interface FindCountyService {
  List<PreviewCountyDto> findAll();
}
