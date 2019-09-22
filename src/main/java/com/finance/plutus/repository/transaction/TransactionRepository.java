package com.finance.plutus.repository.transaction;

import com.finance.plutus.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> { }
