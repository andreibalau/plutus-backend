package com.finance.plutus.service.address.impl;

import com.finance.plutus.model.address.Address;
import com.finance.plutus.model.address.dto.AddressDto;
import com.finance.plutus.model.address.dto.CreateAddressDto;
import com.finance.plutus.repository.address.AddressRepository;
import com.finance.plutus.repository.address.CityRepository;
import com.finance.plutus.repository.address.CountryRepository;
import com.finance.plutus.repository.address.CountyRepository;
import com.finance.plutus.service.address.AddressService;
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
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	private final CityRepository cityRepository;
	private final CountryRepository countryRepository;
	private final CountyRepository countyRepository;

	@Override
	public void create(CreateAddressDto createAddressDto) {
		Address address = createAddressDto.toAddress();
		address.setCity(cityRepository.getOne(createAddressDto.getCity()));
		address.setCounty(countyRepository.getOne(createAddressDto.getCounty()));
		address.setCountry(countryRepository.getOne(createAddressDto.getCountry()));
		addressRepository.save(address);
	}

	@Override
	public List<AddressDto> findAll() {
		return addressRepository
				.findAll()
				.stream()
				.map(AddressDto::from)
				.collect(Collectors.toList());
	}
}
