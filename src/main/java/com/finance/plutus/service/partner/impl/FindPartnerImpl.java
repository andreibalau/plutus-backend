package com.finance.plutus.service.partner.impl;

import com.finance.plutus.model.partner.dto.PartnerDto;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.service.partner.FindPartner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class FindPartnerImpl implements FindPartner {

	private final PartnerRepository partnerRepository;

	@Override
	public List<PartnerDto> find() {
		return partnerRepository
				.findAll()
				.stream()
				.map(PartnerDto::from)
				.collect(Collectors.toList());
	}
}
