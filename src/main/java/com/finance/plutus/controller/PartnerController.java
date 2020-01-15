package com.finance.plutus.controller;

import java.util.List;

import com.finance.plutus.api.PartnerApi;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.partner.dto.CreatePartnerDto;
import com.finance.plutus.model.partner.dto.PartnerDto;
import com.finance.plutus.service.partner.CreatePartnerService;
import com.finance.plutus.service.partner.FindPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RestController
@RequiredArgsConstructor
public class PartnerController implements PartnerApi {

	private final CreatePartnerService createPartnerService;
	private final FindPartnerService findPartnerService;

	public EntityCreatedDto create(CreatePartnerDto createPartnerDto) {
		return createPartnerService.create(createPartnerDto);
	}

	public List<PartnerDto> findAll() {
		return findPartnerService.findAll();
	}

}
