package com.finance.plutus.service.partner.impl;

import com.finance.plutus.exception.PartnerException;
import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.service.partner.DeletePartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
@Service
@RequiredArgsConstructor
public class DeletePartnerServiceImpl implements DeletePartnerService {

	private final PartnerRepository partnerRepository;

	@Override
	public void delete(Long partnerId) {
		Partner partner = partnerRepository
				.findById(partnerId)
				.orElseThrow(PartnerException::partnerNotFound);
		partnerRepository.delete(partner);
	}

}
