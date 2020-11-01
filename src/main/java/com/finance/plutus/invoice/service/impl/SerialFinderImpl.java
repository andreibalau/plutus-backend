package com.finance.plutus.invoice.service.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.invoice.model.Serial;
import com.finance.plutus.invoice.repository.SerialRepository;
import com.finance.plutus.invoice.service.SerialFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class SerialFinderImpl implements SerialFinder {

  private final SerialRepository serialRepository;

  @Override
  public boolean existsByName(String name) {
    return serialRepository.existsByName(name);
  }

  @Override
  public Serial findById(UUID id) {
    return serialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("serial"));
  }

  @Override
  public List<Serial> findAll() {
    return serialRepository.findAll();
  }
}
