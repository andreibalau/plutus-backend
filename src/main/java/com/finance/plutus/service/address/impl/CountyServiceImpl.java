package com.finance.plutus.service.address.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.model.address.dto.CountyDto;
import com.finance.plutus.repository.address.CountyRepository;
import com.finance.plutus.service.address.CountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class CountyServiceImpl implements CountyService {

	private final CountyRepository countyRepository;

	@Override
	public List<CountyDto> findAll() {
		return countyRepository
				.findAll()
				.stream()
				.map(CountyDto::from)
				.collect(Collectors.toList());
	}
}
