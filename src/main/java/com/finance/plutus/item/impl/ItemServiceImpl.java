package com.finance.plutus.item.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.item.Item;
import com.finance.plutus.item.ItemMapper;
import com.finance.plutus.item.ItemRepository;
import com.finance.plutus.item.ItemService;
import com.finance.plutus.item.dto.CreateItemDto;
import com.finance.plutus.item.dto.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemMapper itemMapper;
  private final ItemRepository itemRepository;

  @Override
  @Transactional
  public UUID create(CreateItemDto createItemDto) {
    Item item = itemMapper.mapToEntity(createItemDto);
    itemRepository.save(item);
    return item.getId();
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    Item item = findById(id);
    itemRepository.delete(item);
  }

  @Override
  public Item findById(UUID id) {
    return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("item"));
  }

  @Override
  public List<Item> findAll(int page, int size) {
    return itemRepository.findAll(PageRequest.of(page, size)).toList();
  }

  @Override
  @Transactional
  public void update(UUID id, UpdateItemDto updateItemDto) {
    Item item = findById(id);
    Item updatedItem = itemMapper.mapToEntity(item, updateItemDto);
    itemRepository.save(updatedItem);
  }
}
