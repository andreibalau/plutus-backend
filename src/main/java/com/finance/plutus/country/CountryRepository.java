package com.finance.plutus.country;

import com.finance.plutus.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** Plutus Created by Catalin on 7/1/2020 */
public interface CountryRepository extends JpaRepository<Country, String> {
  Optional<Country> findByCode(String code);
}
