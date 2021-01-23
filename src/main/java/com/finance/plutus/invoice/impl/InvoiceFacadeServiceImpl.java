package com.finance.plutus.invoice.impl;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.invoice.InvoiceFacadeService;
import com.finance.plutus.invoice.InvoiceService;
import com.finance.plutus.invoice.dto.CreateInvoiceDto;
import com.finance.plutus.invoice.dto.InvoiceDto;
import com.finance.plutus.invoice.mapper.InvoiceMapper;
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
public class InvoiceFacadeServiceImpl implements InvoiceFacadeService {

  private final InvoiceService invoiceService;
  private final InvoiceMapper invoiceMapper;

  @Override
  public EntityCreatedResponse create(PlutusRequest<CreateInvoiceDto> request) {
    UUID id = invoiceService.create(request.getData());
    return new EntityCreatedResponse(id);
  }

  @Override
  public PlutusResponse<List<InvoiceDto>> findAll(int page, int size) {
    List<InvoiceDto> invoices =
        invoiceService.findAll(page, size).stream()
            .map(invoiceMapper::mapToDto)
            .collect(Collectors.toList());
    return new PlutusResponse<>(invoices);
  }

  @Override
  public void delete(UUID id) {
    invoiceService.delete(id);
  }

  @Override
  public void collect(Iterable<UUID> ids) {
    invoiceService.collect(ids);
  }

  @Override
  public ResponseEntity<Resource> download(UUID id) {
    byte[] data = invoiceService.download(id);
    return prepareDownloadResponse(data, "invoice.pdf");
  }

  @Override
  public ResponseEntity<Resource> downloadArchive() {
    byte[] data = invoiceService.downloadAll();
    return prepareDownloadResponse(data, "archive.zip");
  }
}
