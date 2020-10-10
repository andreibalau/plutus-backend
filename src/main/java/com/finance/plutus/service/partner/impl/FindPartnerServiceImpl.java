package com.finance.plutus.service.partner.impl;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.PartnerDto;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.partner.FindPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class FindPartnerServiceImpl implements FindPartnerService {

  private final PartnerRepository partnerRepository;

  @Override
  public PartnerDto findDtoById(UUID id) {
    Partner partner = findEntityById(id);
    return PartnerDto.mapFromEntity(partner);
  }

  @Override
  public Partner findEntityById(UUID id) {
    return partnerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("partner"));
  }

  @Override
  public List<PartnerDto> findAllDtoByPage(int page, int size) {
    return partnerRepository.findAll(PageRequest.of(page, size)).stream()
        .map(PartnerDto::mapFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public long countAll() {
    return partnerRepository.count();
  }
}
