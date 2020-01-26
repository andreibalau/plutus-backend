package com.finance.plutus.service.partner.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.exception.PartnerException;
import com.finance.plutus.model.partner.dto.PartnerDto;
import com.finance.plutus.model.partner.dto.PreviewPartnerDto;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.service.partner.FindPartnerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class FindPartnerServiceImpl implements FindPartnerService {

	private final PartnerRepository partnerRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<PreviewPartnerDto> findAll() {
		return partnerRepository
				.findAll()
				.stream()
				.map(partner -> modelMapper.map(partner, PreviewPartnerDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PartnerDto findById(Long partnerId) {
		return partnerRepository
				.findById(partnerId)
				.map(partner -> modelMapper.map(partner, PartnerDto.class))
				.orElseThrow(PartnerException::partnerNotFound);
	}
}
