package com.finance.plutus.service.address.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.model.address.dto.CityDto;
import com.finance.plutus.repository.address.CityRepository;
import com.finance.plutus.service.address.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;

	@Override
	public List<CityDto> findAll() {
		return cityRepository
				.findAll()
				.stream()
				.map(CityDto::from)
				.collect(Collectors.toList());
	}
}
