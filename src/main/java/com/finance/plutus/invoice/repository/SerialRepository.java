package com.finance.plutus.invoice.repository;

import com.finance.plutus.invoice.model.Serial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
public interface SerialRepository extends JpaRepository<Serial, UUID> {
  boolean existsByName(String name);
}
