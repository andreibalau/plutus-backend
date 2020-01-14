package com.finance.plutus.service.address;

import java.util.List;

import com.finance.plutus.model.address.dto.CityDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface CityService {
	List<CityDto> findAll();
}
