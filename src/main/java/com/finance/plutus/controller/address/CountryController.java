package com.finance.plutus.controller.address;

import java.util.List;

import com.finance.plutus.model.address.dto.CountryDto;
import com.finance.plutus.service.address.CountryService;
import com.finance.plutus.util.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping(Api.COUNTRIES)
@RestController
public class CountryController {

	private final CountryService countryService;

	@GetMapping
	public List<CountryDto> findAll() {
		return countryService.findAll();
	}

}
