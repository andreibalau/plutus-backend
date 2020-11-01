package com.finance.plutus.item.service.impl;

import com.finance.plutus.item.model.CreateItemDto;
import com.finance.plutus.item.model.Item;
import com.finance.plutus.item.model.ItemVat;
import com.finance.plutus.item.repository.ItemRepository;
import com.finance.plutus.item.service.ItemCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class ItemCreatorImpl implements ItemCreator {

  private final ItemRepository itemRepository;

  @Override
  @Transactional
  public UUID create(CreateItemDto createItemDto) {
    Item item = createItem(createItemDto);
    itemRepository.save(item);
    return item.getId();
  }

  public Item createItem(CreateItemDto createItemDto) {
    Item item = new Item();
    item.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    item.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    item.setType(createItemDto.getType());
    item.setName(createItemDto.getName());
    item.setDescription(createItemDto.getDescription());
    item.setUnitPrice(createItemDto.getUnitPrice());
    item.setVat(ItemVat.fromAmount(createItemDto.getVat()));
    item.setUom(createItemDto.getUom());
    item.setCode(createItemDto.getCode());
    item.setTotalPrice(item.getVat().getAmount() * item.getUnitPrice() + item.getUnitPrice());
    return item;
  }
}