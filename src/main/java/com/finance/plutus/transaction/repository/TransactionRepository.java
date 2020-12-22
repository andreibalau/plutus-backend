package com.finance.plutus.transaction.repository;

import com.finance.plutus.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface TransactionRepository
    extends JpaRepository<Transaction, UUID>, TransactionRepositoryFilter {}
