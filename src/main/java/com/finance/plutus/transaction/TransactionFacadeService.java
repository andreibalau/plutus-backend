package com.finance.plutus.transaction;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.transaction.dto.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface TransactionFacadeService {
  EntityCreatedResponse create(PlutusRequest<CreateTransactionDto> request);

  PlutusResponse<List<TransactionDto>> findAll(FilterParams filterParams, int page, int size);

  PlutusResponse<TransactionStat> findStat(FilterParams filterParams);

  void update(UUID id, PlutusRequest<UpdateTransactionDto> request);

  void collect(Iterable<UUID> ids);

  void importFile(PlutusRequest<UploadFileDto> request);

  ResponseEntity<Resource> downloadDocument(String year);

  void delete(UUID id);
}
