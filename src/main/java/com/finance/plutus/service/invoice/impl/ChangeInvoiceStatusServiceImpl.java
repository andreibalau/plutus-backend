package com.finance.plutus.service.invoice.impl;

import com.finance.plutus.exception.InvoiceException;
import com.finance.plutus.model.invoice.Invoice;
import com.finance.plutus.model.invoice.Status;
import com.finance.plutus.model.invoice.dto.ChangeStatusDto;
import com.finance.plutus.model.serial.Serial;
import com.finance.plutus.repository.invoice.InvoiceRepository;
import com.finance.plutus.service.invoice.ChangeInvoiceStatusService;
import com.finance.plutus.service.serial.UpdateSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.01.2020
 */
@Service
@RequiredArgsConstructor
public class ChangeInvoiceStatusServiceImpl implements ChangeInvoiceStatusService {

    private final InvoiceRepository invoiceRepository;
    private final UpdateSerialService updateSerialService;

    @Override
    public void changeStatus(Long invoiceId, ChangeStatusDto changeStatusDto) {
        Invoice invoice = invoiceRepository
                .findById(invoiceId)
                .orElseThrow(InvoiceException::invoiceNotFound);
        if (invoice.getStatus() == Status.DONE || invoice.getStatus() == Status.CANCELLED) {
            throw InvoiceException.invoiceCannotBeUpdated();
        }
        if (changeStatusDto.getStatus() == Status.ACTIVE) {
            createAndSetSerialNumber(invoice);
        }
        invoice.setStatus(changeStatusDto.getStatus());
        invoiceRepository.save(invoice);
    }

    private void createAndSetSerialNumber(Invoice invoice) {
        Serial serial = invoice.getSerial();
        long current = serial.getNumber();
        if (current == serial.getNumber()) {
            throw InvoiceException.serialNumberOverflow();
        }
        invoice.setSerialName(serial.getName() + current);
        serial.setNumber(++current);
        updateSerialService.update(serial);
    }

}
