package com.finance.plutus.old.service.item;

import com.finance.plutus.old.model.dto.UpdateItemDto;

import java.util.UUID;

/** Plutus Created by Catalin on 8/7/2020 */
public interface UpdateItemService {
  void update(UUID id, UpdateItemDto updateItemDto);
}
