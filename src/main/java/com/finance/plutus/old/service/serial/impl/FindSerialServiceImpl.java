package com.finance.plutus.old.service.serial.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.old.model.dto.SerialDto;
import com.finance.plutus.invoice.model.Serial;
import com.finance.plutus.invoice.repository.SerialRepository;
import com.finance.plutus.old.service.serial.FindSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
  public Serial findEntityById(UUID id) {
    return serialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("serial"));
  }

  @Override
  public List<SerialDto> findAllDto() {
    return serialRepository.findAll().stream()
        .map(SerialDto::fromEntity)
        .collect(Collectors.toList());
  }
}
