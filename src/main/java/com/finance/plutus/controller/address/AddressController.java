package com.finance.plutus.controller.address;

import com.finance.plutus.model.address.dto.AddressDto;
import com.finance.plutus.model.address.dto.CreateAddressDto;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
@RestController
public class AddressController {

	private final AddressService addressService;

	@ResponseStatus(CREATED)
	@PostMapping
	public EntityCreatedDto create(@Valid @RequestBody CreateAddressDto createAddressDto) {
		return addressService.create(createAddressDto);
	}

	@GetMapping
	public List<AddressDto> findAll() {
		return addressService.findAll();
	}

}
