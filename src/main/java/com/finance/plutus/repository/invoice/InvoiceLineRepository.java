package com.finance.plutus.repository.invoice;

import com.finance.plutus.model.invoice.Line;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface InvoiceLineRepository extends JpaRepository<Line, Long> { }
