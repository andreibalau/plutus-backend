package com.finance.plutus.invoice;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.invoice.dto.CreateInvoiceDto;
import com.finance.plutus.invoice.dto.InvoiceDto;
import com.finance.plutus.invoice.dto.UpdateInvoiceDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface InvoiceFacadeService {
  EntityCreatedResponse create(PlutusRequest<CreateInvoiceDto> request);

  void update(UUID id, PlutusRequest<UpdateInvoiceDto> request);

  PlutusResponse<List<InvoiceDto>> findAll(int page, int size);

  void delete(UUID id);

  void collect(Iterable<UUID> ids);

  ResponseEntity<Resource> download(UUID id);

  ResponseEntity<Resource> downloadArchive();
}
