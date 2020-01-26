package com.finance.plutus.service.partner.impl;

import com.finance.plutus.exception.PartnerException;
import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.model.partner.dto.ModifyPartnerDto;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.service.partner.UpdatePartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
@Service
@RequiredArgsConstructor
public class UpdatePartnerServiceImpl implements UpdatePartnerService {

	private final PartnerRepository partnerRepository;

	@Override
	public void update(Long partnerId, ModifyPartnerDto partnerDto) {
		Partner partner = findPartner(partnerId);
		updatePartner(partner, partnerDto);
	}

	private Partner findPartner(Long partnerId) {
		return partnerRepository
				.findById(partnerId)
				.orElseThrow(PartnerException::partnerNotFound);
	}

	private void updatePartner(Partner partner, ModifyPartnerDto partnerDto) {
		partner.setAddress(partnerDto.getAddress());
		partner.setState(partnerDto.getState());
		partner.setCity(partnerDto.getCity());
		partner.setCountry(partnerDto.getCountry());
		partner.setIban(partnerDto.getIban());
		partner.setCif(partnerDto.getCif());
		partner.setEmail(partnerDto.getEmail());
		partner.setName(partnerDto.getName());
		partner.setPhone(partnerDto.getPhone());
		partner.setRegCom(partnerDto.getRegCom());
		partner.setType(partnerDto.getType());
		partner.setUpdatedOn(System.currentTimeMillis());
		partnerRepository.save(partner);
	}

}
