package com.finance.plutus.service.bank;

import com.finance.plutus.model.dto.BankDto;
import com.finance.plutus.model.entity.Bank;

import java.util.List;
import java.util.Optional;

/** Plutus Created by Catalin on 9/27/2020 */
public interface FindBankService {
  Optional<BankDto> findDtoById(String id);

  Optional<Bank> findEntityById(String id);

  List<BankDto> findAllDto();
}
