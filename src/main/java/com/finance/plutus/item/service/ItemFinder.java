package com.finance.plutus.item.service;

import com.finance.plutus.item.model.Item;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface ItemFinder {
  Item findById(UUID id);

  List<Item> findAll(PageRequest page);

  long count();
}
