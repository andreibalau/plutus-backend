package com.finance.plutus.service.address;

import com.finance.plutus.model.address.dto.CountryDto;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface CountryService {
	List<CountryDto> findAll();
}
