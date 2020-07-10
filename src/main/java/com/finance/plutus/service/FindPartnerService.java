package com.finance.plutus.service;

import com.finance.plutus.model.dto.PartnerDto;
import com.finance.plutus.model.dto.PreviewPartnerDto;
import com.finance.plutus.model.entity.Partner;

import java.util.List;

/** Plutus Created by catalin on 7/2/2020 */
public interface FindPartnerService {
  PartnerDto findDtoById(Long id);

  Partner findById(Long id);

  List<PreviewPartnerDto> findAllByPage(int page, int size);
}
