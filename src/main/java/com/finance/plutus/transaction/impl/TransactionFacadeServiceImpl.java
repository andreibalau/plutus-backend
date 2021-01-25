package com.finance.plutus.transaction.impl;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.transaction.TransactionFacadeService;
import com.finance.plutus.transaction.TransactionMapper;
import com.finance.plutus.transaction.TransactionService;
import com.finance.plutus.transaction.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.finance.plutus.app.util.ResponseEntityUtils.prepareDownloadResponse;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class TransactionFacadeServiceImpl implements TransactionFacadeService {

  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  @Override
  public EntityCreatedResponse create(PlutusRequest<CreateTransactionDto> request) {
    UUID id = transactionService.create(request.getData());
    return new EntityCreatedResponse(id);
  }

  @Override
  public PlutusResponse<List<TransactionDto>> findAll(
      FilterParams filterParams, int page, int size) {
    List<TransactionDto> transactions =
        transactionService.findAll(filterParams, page, size).stream()
            .map(transactionMapper::mapToDto)
            .collect(Collectors.toList());
    return new PlutusResponse<>(transactions);
  }

  @Override
  public PlutusResponse<TransactionStat> findStat(FilterParams filterParams) {
    TransactionStat transactionStat = transactionService.calculateStat(filterParams);
    return new PlutusResponse<>(transactionStat);
  }

  @Override
  public void update(UUID id, PlutusRequest<UpdateTransactionDto> request) {
    transactionService.update(id, request.getData());
  }

  @Override
  public void collect(Iterable<UUID> ids) {
    transactionService.collect(ids);
  }

  @Override
  public void importFile(PlutusRequest<UploadFileDto> request) {
    transactionService.create(request.getData());
  }

  @Override
  public ResponseEntity<Resource> prepareReport() {
    byte[] data = transactionService.downloadAll();
    return prepareDownloadResponse(data, "report.pdf");
  }

  @Override
  public void delete(UUID id) {
    transactionService.delete(id);
  }
}
