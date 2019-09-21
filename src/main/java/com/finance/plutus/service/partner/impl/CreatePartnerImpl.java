package com.finance.plutus.service.partner.impl;

import com.finance.plutus.exception.ServiceException;
import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.model.partner.dto.CreatePartnerDto;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.service.partner.CreatePartner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class CreatePartnerImpl implements CreatePartner {

	private final PartnerRepository partnerRepository;

	@Override
	public void create(CreatePartnerDto createPartnerDto) {
		if (partnerRepository.findByEmail(createPartnerDto.getEmail()).isPresent()) {
			throw ServiceException.emailAlreadyExists();
		}
		Partner partner = createPartnerDto.toPartner();
		partner.setCreatedOn(System.currentTimeMillis());
		partner.setUpdatedOn(System.currentTimeMillis());
		partnerRepository.save(partner);
	}
}
