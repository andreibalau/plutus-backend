package com.finance.plutus.invoice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Plutus Created by catalin on 7/3/2020 */
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {}
