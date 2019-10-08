package com.finance.plutus.service.partner.impl;

import com.finance.plutus.exception.PlutusException;
import com.finance.plutus.model.address.Address;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.model.partner.dto.CreatePartnerDto;
import com.finance.plutus.repository.address.AddressRepository;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.service.partner.CreatePartner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class CreatePartnerImpl implements CreatePartner {

	private final PartnerRepository partnerRepository;
	private final AddressRepository addressRepository;

	@Override
	public EntityCreatedDto create(CreatePartnerDto createPartnerDto) {
		if (partnerRepository.findByEmail(createPartnerDto.getEmail()).isPresent()) {
			throw PlutusException.emailAlreadyExists();
		}
		List<Address> addresses = addressRepository.findAllById(createPartnerDto.getAddresses());
		Partner partner = createPartnerDto.toPartner();
		partner.setAddresses(new HashSet<>(addresses));
		partner.setCreatedOn(System.currentTimeMillis());
		partner.setUpdatedOn(System.currentTimeMillis());
		return new EntityCreatedDto(partnerRepository.save(partner).getId());
	}
}
