package com.finance.plutus.service.bank.impl;

import com.finance.plutus.model.dto.BankDto;
import com.finance.plutus.model.entity.Bank;
import com.finance.plutus.repository.BankRepository;
import com.finance.plutus.service.bank.FindBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 9/27/2020 */
@Service
@RequiredArgsConstructor
public class FindBankServiceImpl implements FindBankService {

  private final BankRepository bankRepository;

  @Override
  public Optional<BankDto> findDtoById(String id) {
    return findEntityById(id).map(BankDto::mapFromEntity);
  }

  @Override
  public Optional<Bank> findEntityById(String id) {
    return bankRepository.findById(id);
  }

  @Override
  public List<BankDto> findAllDto() {
    return bankRepository.findAll().stream()
        .map(BankDto::mapFromEntity)
        .collect(Collectors.toList());
  }
}
