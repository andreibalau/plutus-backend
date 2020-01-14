package com.finance.plutus.controller.address;

import java.util.List;

import com.finance.plutus.model.address.dto.CityDto;
import com.finance.plutus.service.address.CityService;
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
@RequestMapping(Api.CITIES)
@RestController
public class CityController {

	private final CityService cityService;

	@GetMapping
	public List<CityDto> findAll() {
		return cityService.findAll();
	}
}
