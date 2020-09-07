package com.finance.plutus.service.item;

import java.util.List;

import com.finance.plutus.model.dto.ItemDto;
import com.finance.plutus.model.dto.PreviewItemDto;
import com.finance.plutus.model.entity.Item;

/** Plutus Created by catalin on 7/2/2020 */
public interface FindItemService {
  ItemDto findDtoById(Long id);

  Item findById(Long id);

  List<PreviewItemDto> findAllByPage(int page, int size);

  List<PreviewItemDto> findAll();

  int countAll();
}
