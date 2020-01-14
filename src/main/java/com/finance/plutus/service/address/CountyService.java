package com.finance.plutus.service.address;

import java.util.List;

import com.finance.plutus.model.address.dto.CountyDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface CountyService {
	List<CountyDto> findAll();
}
