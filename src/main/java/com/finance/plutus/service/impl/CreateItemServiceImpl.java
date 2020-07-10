package com.finance.plutus.service.impl;

import com.finance.plutus.model.dto.CreateItemDto;
import com.finance.plutus.model.entity.Item;
import com.finance.plutus.repository.ItemRepository;
import com.finance.plutus.service.CreateItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class CreateItemServiceImpl implements CreateItemService {

  private final ItemRepository itemRepository;

  @Override
  public Long create(CreateItemDto createItemDto) {
    Item item = createItem(createItemDto);
    itemRepository.save(item);
    return item.getId();
  }

  public Item createItem(CreateItemDto createItemDto) {
    Item item = new Item();
    item.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    item.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    item.setItemType(createItemDto.getItemType());
    item.setName(createItemDto.getName());
    item.setUnitPrice(createItemDto.getUnitPrice());
    item.setVat(createItemDto.getVat());
    item.setUom(createItemDto.getUom());
    item.setTotalPrice(item.getVat() * item.getUnitPrice() + item.getUnitPrice());
    return item;
  }
}
