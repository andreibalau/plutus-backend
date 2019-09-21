package com.finance.plutus.controller.partner;

import com.finance.plutus.model.partner.dto.CreatePartnerDto;
import com.finance.plutus.service.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping("/api/partners")
@RestController
public class PartnerController {

	private final PartnerService partnerService;

	@ResponseStatus(CREATED)
	@PostMapping("/create")
	public void create(@Valid @RequestBody CreatePartnerDto createPartnerDto) {
		partnerService.create(createPartnerDto);
	}

}
