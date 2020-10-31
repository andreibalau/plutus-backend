package com.finance.plutus.item.service;

import com.finance.plutus.item.model.CreateItemDto;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface ItemCreator {
  UUID create(CreateItemDto item);
}
