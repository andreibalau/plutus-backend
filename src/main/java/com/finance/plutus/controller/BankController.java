package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.FindBanksResponse;
import com.finance.plutus.model.dto.BankDto;
import com.finance.plutus.service.bank.FindBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by Catalin on 10/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/banks")
public class BankController {

  private final FindBankService findBankService;

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindBanksResponse findAll() {
    List<BankDto> banks = findBankService.findAllDto();
    return new FindBanksResponse(banks);
  }
}
