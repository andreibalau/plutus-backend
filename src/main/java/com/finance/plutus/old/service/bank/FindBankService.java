package com.finance.plutus.old.service.bank;

import com.finance.plutus.bank.model.BankDto;
import com.finance.plutus.bank.model.Bank;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 9/27/2020 */
public interface FindBankService {
  BankDto findDtoById(UUID id);

  Bank findEntityById(UUID id);

  List<BankDto> findAllDto();
}
