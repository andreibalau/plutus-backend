package com.finance.plutus.bank.infrastructure.adapters.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Plutus Created by Catalin on 9/27/2020 */
public interface BankRepository extends JpaRepository<Bank, UUID> {}
