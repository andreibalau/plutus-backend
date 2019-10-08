package com.finance.plutus.repository.history;

import com.finance.plutus.model.history.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
public interface OperationRepository extends JpaRepository<Operation, Long> { }
