package com.finance.plutus.old.repository;

import com.finance.plutus.old.model.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Plutus Created by Catalin on 10/13/2020 */
public interface BusinessRepository extends JpaRepository<Business, UUID> {}
