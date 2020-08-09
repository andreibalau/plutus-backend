package com.finance.plutus.service.item.impl;

import com.finance.plutus.model.dto.UpdateItemDto;
import com.finance.plutus.model.entity.Item;
import com.finance.plutus.repository.ItemRepository;
import com.finance.plutus.service.item.FindItemService;
import com.finance.plutus.service.item.UpdateItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by Catalin on 8/7/2020 */
@Service
@RequiredArgsConstructor
public class UpdateItemServiceImpl implements UpdateItemService {

  private final ItemRepository itemRepository;
  private final FindItemService findItemService;

  @Override
  public void update(Long id, UpdateItemDto updateItemDto) {
    Item item = findItemService.findById(id);
    item.setType(updateItemDto.getType());
    item.setName(updateItemDto.getName());
    item.setUnitPrice(updateItemDto.getUnitPrice());
    item.setVat(updateItemDto.getVat());
    item.setUom(updateItemDto.getUom());
    item.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    item.setTotalPrice(item.getVat() * item.getUnitPrice() + item.getUnitPrice());
    itemRepository.save(item);
  }
}
