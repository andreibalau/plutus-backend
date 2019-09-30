package com.finance.plutus.repository.exchange;

import com.finance.plutus.model.exchange.ExchangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 30.09.2019
 */
public interface ExchangeRepository extends JpaRepository<ExchangeHistory, Long> { }
