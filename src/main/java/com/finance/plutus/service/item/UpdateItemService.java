package com.finance.plutus.service.item;

import com.finance.plutus.model.dto.UpdateItemDto;

/** Plutus Created by Catalin on 8/7/2020 */
public interface UpdateItemService {
  void update(String id, UpdateItemDto updateItemDto);
}
