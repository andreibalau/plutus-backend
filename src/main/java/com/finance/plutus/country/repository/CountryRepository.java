package com.finance.plutus.country.repository;

import com.finance.plutus.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/** Plutus Created by Catalin on 7/1/2020 */
public interface CountryRepository extends JpaRepository<Country, String> {}
