package com.finance.plutus.old.service.item;

import com.finance.plutus.old.model.dto.CreateItemDto;

import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
public interface CreateItemService {
  UUID create(CreateItemDto createItemDto);
}
