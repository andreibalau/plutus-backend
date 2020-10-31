package com.finance.plutus.item.service.impl;

import com.finance.plutus.item.model.Item;
import com.finance.plutus.item.repository.ItemRepository;
import com.finance.plutus.item.service.ItemCleaner;
import com.finance.plutus.item.service.ItemFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class ItemCleanerImpl implements ItemCleaner {

  private final ItemFinder itemFinder;
  private final ItemRepository itemRepository;

  @Override
  @Transactional
  public void delete(UUID id) {
    Item item = itemFinder.findById(id);
    itemRepository.delete(item);
  }
}
