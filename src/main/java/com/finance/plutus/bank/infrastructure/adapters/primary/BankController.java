package com.finance.plutus.bank.infrastructure.adapters.primary;

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
  public List<BankDto> findAll() {
    return bankService.findAll();
  }
}
