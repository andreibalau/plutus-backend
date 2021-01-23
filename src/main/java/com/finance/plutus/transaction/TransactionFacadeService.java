package com.finance.plutus.transaction;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.transaction.dto.CreateTransactionDto;
import com.finance.plutus.transaction.dto.TransactionDto;
import com.finance.plutus.transaction.dto.UpdateTransactionDto;
import com.finance.plutus.transaction.dto.UploadFileDto;
import com.finance.plutus.transaction.model.TransactionType;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface TransactionFacadeService {
  EntityCreatedResponse create(PlutusRequest<CreateTransactionDto> request);

  PlutusResponse<List<TransactionDto>> findAll(
      int page,
      int size,
      UUID partnerId,
      TransactionType type,
      LocalDate startDate,
      LocalDate endDate);

  void update(UUID id, PlutusRequest<UpdateTransactionDto> request);

  void collect(Iterable<UUID> ids);

  void importFile(PlutusRequest<UploadFileDto> request);

  ResponseEntity<Resource> prepareReport();

  void delete(UUID id);
}
