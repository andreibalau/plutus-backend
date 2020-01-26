package com.finance.plutus.service.partner;

import java.util.List;

import com.finance.plutus.model.partner.dto.PartnerDto;
import com.finance.plutus.model.partner.dto.PreviewPartnerDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface FindPartnerService {
	List<PreviewPartnerDto> findAll();
	PartnerDto findById(Long partnerId);
}
