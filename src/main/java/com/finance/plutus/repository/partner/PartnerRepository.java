package com.finance.plutus.repository.partner;

import com.finance.plutus.model.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface PartnerRepository extends JpaRepository<Partner, Long> {
	Optional<Partner> findByEmail(String email);
}
