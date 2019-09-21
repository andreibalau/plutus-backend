package com.finance.plutus.service.address.impl;

import com.finance.plutus.model.address.dto.CountryDto;
import com.finance.plutus.repository.address.CountryRepository;
import com.finance.plutus.service.address.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

	private final CountryRepository countryRepository;

	@Override
	public List<CountryDto> findAll() {
		return countryRepository
				.findAll()
				.stream()
				.map(CountryDto::from)
				.collect(Collectors.toList());
	}
}
