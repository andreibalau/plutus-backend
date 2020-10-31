package com.finance.plutus.item.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.item.model.Item;
import com.finance.plutus.item.repository.ItemRepository;
import com.finance.plutus.item.service.ItemFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class ItemFinderImpl implements ItemFinder {

  private final ItemRepository itemRepository;

  @Override
  public Item findById(UUID id) {
    return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("item"));
  }

  @Override
  public List<Item> findAll(PageRequest page) {
    return itemRepository.findAll(page).stream().collect(Collectors.toList());
  }

  @Override
  public long count() {
    return itemRepository.count();
  }
}
