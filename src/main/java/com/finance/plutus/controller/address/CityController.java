package com.finance.plutus.controller.address;

import com.finance.plutus.model.address.dto.CityDto;
import com.finance.plutus.service.address.CityService;
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
@RequestMapping("/api/v1/cities")
@RestController
public class CityController {

	private final CityService cityService;

	@GetMapping
	public List<CityDto> findAll() {
		return cityService.findAll();
	}
}
