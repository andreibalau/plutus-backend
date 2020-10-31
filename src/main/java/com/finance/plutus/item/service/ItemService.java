package com.finance.plutus.item.service;

import com.finance.plutus.item.model.CreateItemDto;
import com.finance.plutus.item.model.ItemDto;
import com.finance.plutus.item.model.UpdateItemDto;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface ItemService {
  UUID create(CreateItemDto item);

  void update(UUID id, UpdateItemDto item);

  void delete(UUID id);

  ItemDto findById(UUID id);

  List<ItemDto> findAll(int page, int size);

  long count();
}
