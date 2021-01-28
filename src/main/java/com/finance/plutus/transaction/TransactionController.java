package com.finance.plutus.transaction;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.transaction.dto.CreateTransactionDto;
import com.finance.plutus.transaction.dto.FilterParams;
import com.finance.plutus.transaction.dto.TransactionDto;
import com.finance.plutus.transaction.dto.TransactionStat;
import com.finance.plutus.transaction.dto.UpdateTransactionDto;
import com.finance.plutus.transaction.dto.UploadFileDto;
import com.finance.plutus.transaction.model.TransactionType;
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
import java.time.LocalDate;
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

  private final TransactionFacadeService transactionFacadeService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(
      @Valid @RequestBody PlutusRequest<CreateTransactionDto> request) {
    return transactionFacadeService.create(request);
  }

  @PutMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(
      @PathVariable UUID id, @Valid @RequestBody PlutusRequest<UpdateTransactionDto> request) {
    transactionFacadeService.update(id, request);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public PlutusResponse<List<TransactionDto>> findAll(
      @RequestParam Integer page,
      @RequestParam Integer size,
      @RequestParam(required = false) UUID partnerId,
      @RequestParam(required = false) TransactionType type,
      @RequestParam(required = false) Boolean deductible,
      @RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate) {
    FilterParams params =
        FilterParams.builder()
            .partnerId(partnerId)
            .type(type)
            .deductible(deductible)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    return transactionFacadeService.findAll(params, page, size);
  }

  @GetMapping(
      value = "/stats",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public PlutusResponse<TransactionStat> findStats(
      @RequestParam(required = false) UUID partnerId,
      @RequestParam(required = false) TransactionType type,
      @RequestParam(required = false) Boolean deductible,
      @RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate) {
    FilterParams params =
        FilterParams.builder()
            .partnerId(partnerId)
            .type(type)
            .deductible(deductible)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    return transactionFacadeService.findStat(params);
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable UUID id) {
    transactionFacadeService.delete(id);
  }

  @PostMapping(
      value = "/cashing",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void collect(@RequestParam List<UUID> ids) {
    transactionFacadeService.collect(ids);
  }

  @ResponseStatus(CREATED)
  @PostMapping(
      value = "/file",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void importFile(@Valid @RequestBody PlutusRequest<UploadFileDto> request) {
    transactionFacadeService.importFile(request);
  }
}
