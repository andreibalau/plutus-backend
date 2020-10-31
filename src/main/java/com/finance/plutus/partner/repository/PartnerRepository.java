package com.finance.plutus.partner.repository;

import com.finance.plutus.partner.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
public interface PartnerRepository extends JpaRepository<Partner, UUID> {}
