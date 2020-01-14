package com.finance.plutus.service.address;

import java.util.List;

import com.finance.plutus.model.address.dto.AddressDto;
import com.finance.plutus.model.address.dto.CreateAddressDto;
import com.finance.plutus.model.common.EntityCreatedDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface AddressService {
	EntityCreatedDto create(CreateAddressDto createAddressDto);
	List<AddressDto> findAll();
}
