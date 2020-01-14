package com.finance.plutus.controller.address;

import java.util.List;

import com.finance.plutus.model.address.dto.CountyDto;
import com.finance.plutus.service.address.CountyService;
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
@RequestMapping(Api.COUNTIES)
@RestController
public class CountyController {

	private final CountyService countyService;

	@GetMapping
	public List<CountyDto> findAll() {
		return countyService.findAll();
	}

}
