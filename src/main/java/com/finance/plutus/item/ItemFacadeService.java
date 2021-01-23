package com.finance.plutus.item;

import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.item.dto.CreateItemDto;
import com.finance.plutus.item.dto.ItemDto;
import com.finance.plutus.item.dto.UpdateItemDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface ItemFacadeService {
  EntityCreatedResponse create(PlutusRequest<CreateItemDto> request);

  void update(UUID id, PlutusRequest<UpdateItemDto> request);

  void delete(UUID id);

  PlutusResponse<List<ItemDto>> findAll(int page, int size);
}
