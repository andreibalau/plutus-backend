package com.finance.plutus.item.service.impl;

import com.finance.plutus.item.model.CreateItemDto;
import com.finance.plutus.item.model.Item;
import com.finance.plutus.item.model.ItemDto;
import com.finance.plutus.item.model.UpdateItemDto;
import com.finance.plutus.item.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemCreator itemCreator;
  private final ItemUpdater itemUpdater;
  private final ItemFinder itemFinder;
  private final ItemCleaner itemCleaner;

  @Override
  public UUID create(CreateItemDto item) {
    return itemCreator.create(item);
  }

  @Override
  public void update(UUID id, UpdateItemDto item) {
    itemUpdater.update(id, item);
  }

  @Override
  public void delete(UUID id) {
    itemCleaner.delete(id);
  }

  @Override
  public ItemDto findById(UUID id) {
    Item item = itemFinder.findById(id);
    return ItemDto.mapFromEntity(item);
  }

  @Override
  public List<ItemDto> findAll(int page, int size) {
    return itemFinder.findAll(PageRequest.of(page, size)).stream()
        .map(ItemDto::mapFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public long count() {
    return itemFinder.count();
  }
}
