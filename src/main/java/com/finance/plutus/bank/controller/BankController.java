package com.finance.plutus.bank.controller;

import com.finance.plutus.bank.controller.payload.FindBanksResponse;
import com.finance.plutus.bank.model.BankDto;
import com.finance.plutus.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by Catalin on 10/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/banks")
public class BankController {

  private final BankService bankService;

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindBanksResponse findAll() {
    List<BankDto> banks = bankService.findAll();
    return new FindBanksResponse(banks);
  }
}
