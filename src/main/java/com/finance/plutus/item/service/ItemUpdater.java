package com.finance.plutus.item.service;

import com.finance.plutus.item.model.UpdateItemDto;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface ItemUpdater {
  void update(UUID id, UpdateItemDto item);
}
