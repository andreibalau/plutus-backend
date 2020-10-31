package com.finance.plutus.invoice.service;

import com.finance.plutus.invoice.model.Serial;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface SerialFinder {
  boolean existsByName(String name);

  Serial findById(UUID id);

  List<Serial> findAll();
}
