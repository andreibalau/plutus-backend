package com.finance.plutus.service.address;

import java.util.List;

import com.finance.plutus.model.address.dto.CountryDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface CountryService {
	List<CountryDto> findAll();
}
