package com.finance.plutus.controller.address;

import com.finance.plutus.model.address.dto.CountryDto;
import com.finance.plutus.service.address.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping("/api/countries")
@RestController
public class CountryController {

	private CountryService countryService;

	@GetMapping("/")
	public List<CountryDto> findAll() {
		return countryService.findAll();
	}

}
