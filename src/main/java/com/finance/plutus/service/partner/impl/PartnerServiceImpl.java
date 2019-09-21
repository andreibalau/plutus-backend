package com.finance.plutus.service.partner.impl;

import com.finance.plutus.model.partner.dto.CreatePartnerDto;
import com.finance.plutus.service.partner.CreatePartner;
import com.finance.plutus.service.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

	private final CreatePartner createPartner;

	@Override
	public void create(CreatePartnerDto createPartnerDto) {
		createPartner.create(createPartnerDto);
	}
}
