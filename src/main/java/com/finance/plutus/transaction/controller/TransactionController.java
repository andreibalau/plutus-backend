package com.finance.plutus.transaction.controller;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.transaction.controller.payload.CreateTransactionRequest;
import com.finance.plutus.transaction.controller.payload.FindTransactionsResponse;
import com.finance.plutus.transaction.controller.payload.ImportFileRequest;
import com.finance.plutus.transaction.controller.payload.UpdateTransactionRequest;
import com.finance.plutus.transaction.model.FilterTransactionDto;
import com.finance.plutus.transaction.model.TransactionDto;
import com.finance.plutus.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by Catalin on 11/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateTransactionRequest request) {
    UUID id = transactionService.create(request.getTransaction());
    return new EntityCreatedResponse(id);
  }

  @PutMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(@PathVariable UUID id, @Valid @RequestBody UpdateTransactionRequest request) {
    transactionService.update(id, request.getTransaction());
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      params = {"page", "size"})
  public FindTransactionsResponse findAll(@RequestParam Integer page, @RequestParam Integer size) {
    List<TransactionDto> transactions = transactionService.findAll(page, size);
    long total = transactionService.count();
    return FindTransactionsResponse.builder()
        .page(page)
        .size(size)
        .total(total)
        .transactions(transactions)
        .build();
  }

  @PostMapping(
      path = "/filter",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      params = {"page", "size"})
  public FindTransactionsResponse findAllFiltered(
      @RequestParam Integer page,
      @RequestParam Integer size,
      @RequestBody FilterTransactionDto filter) {
    List<TransactionDto> transactions = transactionService.findAllFiltered(page, size, filter);
    long total = transactionService.countWithFilter(filter);
    return FindTransactionsResponse.builder()
        .page(page)
        .size(size)
        .total(total)
        .transactions(transactions)
        .build();
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable UUID id) {
    transactionService.delete(id);
  }

  @PostMapping(
      value = "/done/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void markAsDone(@PathVariable UUID id) {
    transactionService.markAsDone(id);
  }

  @PostMapping(
      value = "/done",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void markAsDone(@RequestParam List<UUID> ids) {
    transactionService.markAsDone(ids);
  }

  @ResponseStatus(CREATED)
  @PostMapping(
      value = "/import",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void importFile(@Valid @RequestBody ImportFileRequest request) {
    transactionService.importFile(request.getTransactionsFile());
  }
}
