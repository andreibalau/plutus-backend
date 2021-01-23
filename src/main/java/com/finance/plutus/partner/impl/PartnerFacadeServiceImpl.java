package com.finance.plutus.partner.impl;

import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.partner.PartnerFacadeService;
import com.finance.plutus.partner.PartnerMapper;
import com.finance.plutus.partner.PartnerService;
import com.finance.plutus.partner.dto.CreatePartnerDto;
import com.finance.plutus.partner.dto.PartnerDto;
import com.finance.plutus.partner.dto.UpdatePartnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class PartnerFacadeServiceImpl implements PartnerFacadeService {

  private final PartnerService partnerService;
  private final PartnerMapper partnerMapper;

  @Override
  public EntityCreatedResponse create(PlutusRequest<CreatePartnerDto> request) {
    UUID id = partnerService.create(request.getData());
    return new EntityCreatedResponse(id);
  }

  @Override
  public void update(UUID id, PlutusRequest<UpdatePartnerDto> request) {
    partnerService.update(id, request.getData());
  }

  @Override
  public void delete(UUID id) {
    partnerService.delete(id);
  }

  @Override
  public PlutusResponse<List<PartnerDto>> findAll(int page, int size) {
    List<PartnerDto> partners =
        partnerService.findAll(page, size).stream()
            .map(partnerMapper::mapToDto)
            .collect(Collectors.toList());
    return new PlutusResponse<>(partners);
  }
}
