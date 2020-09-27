package com.finance.plutus.repository;

import com.finance.plutus.model.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

/** Plutus Created by Catalin on 9/27/2020 */
public interface BankRepository extends JpaRepository<Bank, String> {}
