package com.finance.plutus.service.item.impl;

import com.finance.plutus.model.dto.UpdateItemDto;
import com.finance.plutus.model.entity.Item;
import com.finance.plutus.model.entity.ItemVat;
import com.finance.plutus.repository.ItemRepository;
import com.finance.plutus.service.item.FindItemService;
import com.finance.plutus.service.item.UpdateItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/** Plutus Created by Catalin on 8/7/2020 */
@Service
@RequiredArgsConstructor
public class UpdateItemServiceImpl implements UpdateItemService {

  private final ItemRepository itemRepository;
  private final FindItemService findItemService;

  @Override
  @Transactional
  public void update(UUID id, UpdateItemDto updateItemDto) {
    Item item = findItemService.findEntityById(id);
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
