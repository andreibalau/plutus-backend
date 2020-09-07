package com.finance.plutus.service.serial.impl;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.entity.Serial;
import com.finance.plutus.repository.SerialRepository;
import com.finance.plutus.service.serial.FindSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 9/7/2020 */
@Service
@RequiredArgsConstructor
public class FindSerialServiceImpl implements FindSerialService {

  private final SerialRepository serialRepository;

  @Override
  public boolean existsByName(String name) {
    return serialRepository.existsByName(name);
  }

  @Override
  public Serial findById(Long id) {
    return serialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("serial"));
  }
}
