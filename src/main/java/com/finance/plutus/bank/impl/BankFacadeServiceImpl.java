package com.finance.plutus.bank.impl;

import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.bank.BankDto;
import com.finance.plutus.bank.BankFacadeService;
import com.finance.plutus.bank.BankMapper;
import com.finance.plutus.bank.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 1/23/2021 */
@Service
@RequiredArgsConstructor
public class BankFacadeServiceImpl implements BankFacadeService {

  private final BankMapper bankMapper;
  private final BankService bankService;

  @Override
  public PlutusResponse<List<BankDto>> findAll() {
    List<BankDto> banks =
        bankService.findAll().stream().map(bankMapper::mapToDto).collect(Collectors.toList());
    return new PlutusResponse<>(banks);
  }
}
