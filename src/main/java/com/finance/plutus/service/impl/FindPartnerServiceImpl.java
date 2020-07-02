package com.finance.plutus.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.PartnerDto;
import com.finance.plutus.model.dto.PreviewPartnerDto;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.FindPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class FindPartnerServiceImpl implements FindPartnerService {

  private final PartnerRepository partnerRepository;

  @Override
  public PartnerDto findDtoById(Long id) {
    Partner partner = findById(id);
    return map(partner);
  }

  @Override
  public Partner findById(Long id) {
    return partnerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("partner"));
  }

  @Override
  public List<PreviewPartnerDto> findAllByPage(int page, int size) {
    return partnerRepository.findAll(PageRequest.of(page, size)).stream()
        .map(p -> new PreviewPartnerDto(p.getId(), p.getName(), p.getEmail()))
        .collect(Collectors.toList());
  }

  private PartnerDto map(Partner partner) {
    PartnerDto partnerDto = new PartnerDto();
    partnerDto.setId(partner.getId());
    partnerDto.setCreatedOn(partner.getCreatedOn());
    partnerDto.setUpdatedOn(partner.getUpdatedOn());
    partnerDto.setEmail(partner.getEmail());
    partnerDto.setPhone(partner.getPhone());
    partnerDto.setType(partner.getType());
    partnerDto.setName(partner.getName());
    return partnerDto;
  }
}
