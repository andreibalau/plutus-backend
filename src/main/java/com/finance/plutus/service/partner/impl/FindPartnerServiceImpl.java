package com.finance.plutus.service.partner.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.model.partner.dto.PartnerDto;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.service.partner.FindPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class FindPartnerServiceImpl implements FindPartnerService {

	private final PartnerRepository partnerRepository;

	@Override
	public List<PartnerDto> findAll() {
		return partnerRepository
				.findAll()
				.stream()
				.map(PartnerDto::from)
				.collect(Collectors.toList());
	}
}
