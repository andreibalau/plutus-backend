package com.finance.plutus.service.address;

import com.finance.plutus.model.address.dto.AddressDto;
import com.finance.plutus.model.address.dto.CreateAddressDto;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface AddressService {
	void create(CreateAddressDto createAddressDto);
	List<AddressDto> findAll();
}
