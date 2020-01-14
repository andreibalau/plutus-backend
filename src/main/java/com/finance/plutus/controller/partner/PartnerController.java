package com.finance.plutus.controller.partner;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;
import java.util.List;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.partner.dto.CreatePartnerDto;
import com.finance.plutus.model.partner.dto.PartnerDto;
import com.finance.plutus.service.partner.CreatePartnerService;
import com.finance.plutus.service.partner.FindPartnerService;
import com.finance.plutus.util.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping(Api.PARTNERS)
@RestController
public class PartnerController {

	private final CreatePartnerService createPartnerService;
	private final FindPartnerService findPartnerService;

	@ResponseStatus(CREATED)
	@PostMapping
	public EntityCreatedDto create(@Valid @RequestBody CreatePartnerDto createPartnerDto) {
		return createPartnerService.create(createPartnerDto);
	}

	@GetMapping
	public List<PartnerDto> findAll() {
		return findPartnerService.findAll();
	}

}
