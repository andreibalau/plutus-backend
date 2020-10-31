package com.finance.plutus.bank.controller.payload;

import com.finance.plutus.bank.model.BankDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Plutus Created by Catalin on 10/2/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindBanksResponse {
  private List<BankDto> banks;
}
