package com.finance.plutus.service.invoice;

import com.finance.plutus.model.invoice.dto.ChangeStatusDto;

/**
 * Plutus
 * Created by catalin on 22.01.2020
 */
public interface ChangeInvoiceStatusService {
    void changeStatus(Long invoiceId, ChangeStatusDto changeStatusDto);
}
