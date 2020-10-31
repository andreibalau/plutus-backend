package com.finance.plutus.old.service.item;

import com.finance.plutus.old.model.dto.ItemDto;
import com.finance.plutus.old.model.entity.Item;

import java.util.List;
import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
public interface FindItemService {
  ItemDto findDtoById(UUID id);

  Item findEntityById(UUID id);

  List<ItemDto> findAllDtoByPage(int page, int size);

  long countAll();
}
