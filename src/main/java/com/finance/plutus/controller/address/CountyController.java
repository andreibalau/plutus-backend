package com.finance.plutus.controller.address;

import com.finance.plutus.model.address.dto.CountyDto;
import com.finance.plutus.service.address.CountyService;
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
@RequestMapping("/api/v1/counties")
@RestController
public class CountyController {

	private final CountyService countyService;

	@GetMapping
	public List<CountyDto> findAll() {
		return countyService.findAll();
	}

}
