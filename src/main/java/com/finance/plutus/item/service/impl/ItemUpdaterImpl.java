package com.finance.plutus.item.service.impl;

import com.finance.plutus.item.model.Item;
import com.finance.plutus.item.model.ItemVat;
import com.finance.plutus.item.model.UpdateItemDto;
import com.finance.plutus.item.repository.ItemRepository;
import com.finance.plutus.item.service.ItemFinder;
import com.finance.plutus.item.service.ItemUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class ItemUpdaterImpl implements ItemUpdater {

  private final ItemFinder itemFinder;
  private final ItemRepository itemRepository;

  @Override
  @Transactional
  public void update(UUID id, UpdateItemDto updateItemDto) {
    Item item = itemFinder.findById(id);
    item.setType(updateItemDto.getType());
    item.setName(updateItemDto.getName());
    item.setDescription(updateItemDto.getDescription());
    item.setUnitPrice(updateItemDto.getUnitPrice());
    item.setVat(ItemVat.fromAmount(updateItemDto.getVat()));
    item.setUom(updateItemDto.getUom());
    item.setCode(updateItemDto.getCode());
    item.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    item.setTotalPrice(item.getVat().getAmount() * item.getUnitPrice() + item.getUnitPrice());
    itemRepository.save(item);
  }
}
