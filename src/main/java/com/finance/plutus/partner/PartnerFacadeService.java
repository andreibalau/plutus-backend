package com.finance.plutus.partner;

import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.partner.dto.CreatePartnerDto;
import com.finance.plutus.partner.dto.PartnerDto;
import com.finance.plutus.partner.dto.UpdatePartnerDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface PartnerFacadeService {
  EntityCreatedResponse create(PlutusRequest<CreatePartnerDto> request);

  void update(UUID id, PlutusRequest<UpdatePartnerDto> request);

  void delete(UUID id);

  PlutusResponse<List<PartnerDto>> findAll(int page, int size);
}
