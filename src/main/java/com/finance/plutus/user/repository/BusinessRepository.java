package com.finance.plutus.user.repository;

import com.finance.plutus.user.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/** Plutus Created by Catalin on 10/13/2020 */
public interface BusinessRepository extends JpaRepository<Business, UUID> {
  Optional<Business> findByUserId(UUID userId);
}
