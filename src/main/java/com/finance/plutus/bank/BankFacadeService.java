package com.finance.plutus.bank;

import com.finance.plutus.app.payload.PlutusResponse;

import java.util.List;

/** Plutus Created by Catalin on 1/23/2021 */
public interface BankFacadeService {
  PlutusResponse<List<BankDto>> findAll();
}
