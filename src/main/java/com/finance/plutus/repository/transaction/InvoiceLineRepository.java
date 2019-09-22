package com.finance.plutus.repository.transaction;

import com.finance.plutus.model.transaction.invoice.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Long> { }
