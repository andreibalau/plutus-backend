package com.finance.plutus.service.partner.impl;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.partner.dto.CreatePartnerDto;
import com.finance.plutus.model.partner.dto.PartnerDto;
import com.finance.plutus.service.partner.CreatePartner;
import com.finance.plutus.service.partner.FindPartner;
import com.finance.plutus.service.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

	private final CreatePartner createPartner;
	private final FindPartner findPartner;

	@Override
	public EntityCreatedDto create(CreatePartnerDto createPartnerDto) {
		return createPartner.create(createPartnerDto);
	}

	@Override
	public List<PartnerDto> find() {
		return findPartner.find();
	}
}
