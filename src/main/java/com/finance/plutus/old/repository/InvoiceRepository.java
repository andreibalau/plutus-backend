package com.finance.plutus.old.repository;

import com.finance.plutus.old.model.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Plutus Created by catalin on 7/3/2020 */
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
  Page<Invoice> findAll(Pageable pageable);
}
