package com.finance.plutus.controller;

import java.util.List;

import com.finance.plutus.api.PartnerApi;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.partner.dto.ModifyPartnerDto;
import com.finance.plutus.model.partner.dto.PartnerDto;
import com.finance.plutus.model.partner.dto.PreviewPartnerDto;
import com.finance.plutus.service.partner.CreatePartnerService;
import com.finance.plutus.service.partner.DeletePartnerService;
import com.finance.plutus.service.partner.FindPartnerService;
import com.finance.plutus.service.partner.UpdatePartnerService;
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
	private final DeletePartnerService deletePartnerService;
	private final UpdatePartnerService updatePartnerService;

	@Override
	public EntityCreatedDto create(ModifyPartnerDto partnerDto) {
		return createPartnerService.create(partnerDto);
	}

	@Override
	public List<PreviewPartnerDto> findAll() {
		return findPartnerService.findAll();
	}

	@Override
	public PartnerDto findById(Long partnerId) {
		return findPartnerService.findById(partnerId);
	}

	@Override
	public void update(Long partnerId, ModifyPartnerDto partnerDto) {
		updatePartnerService.update(partnerId, partnerDto);
	}

	@Override
	public void delete(Long partnerId) {
		deletePartnerService.delete(partnerId);
	}

}
