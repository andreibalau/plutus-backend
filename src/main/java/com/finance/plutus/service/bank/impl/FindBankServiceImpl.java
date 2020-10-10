package com.finance.plutus.service.bank.impl;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.BankDto;
import com.finance.plutus.model.entity.Bank;
import com.finance.plutus.repository.BankRepository;
import com.finance.plutus.service.bank.FindBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 9/27/2020 */
@Service
@RequiredArgsConstructor
public class FindBankServiceImpl implements FindBankService {

  private final BankRepository bankRepository;

  @Override
  public BankDto findDtoById(String id) {
    Bank bank = findEntityById(id);
    return BankDto.mapFromEntity(bank);
  }

  @Override
  public Bank findEntityById(String id) {
    return bankRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("bank"));
  }

  @Override
  public List<BankDto> findAllDto() {
    return bankRepository.findAll().stream()
        .map(BankDto::mapFromEntity)
        .collect(Collectors.toList());
  }
}
