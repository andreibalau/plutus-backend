package com.finance.plutus.item;

import com.finance.plutus.item.dto.CreateItemDto;
import com.finance.plutus.item.dto.UpdateItemDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
public interface ItemService {
  UUID create(CreateItemDto createItemDto);

  void delete(UUID id);

  Item findById(UUID id);

  List<Item> findAll(int page, int size);

  void update(UUID id, UpdateItemDto updateItemDto);
}
