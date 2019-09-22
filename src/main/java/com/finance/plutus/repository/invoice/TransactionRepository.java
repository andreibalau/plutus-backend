package com.finance.plutus.repository.invoice;

import com.finance.plutus.model.invoice.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> { }
