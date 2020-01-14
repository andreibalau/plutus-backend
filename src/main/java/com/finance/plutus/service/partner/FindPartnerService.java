package com.finance.plutus.service.partner;

import java.util.List;

import com.finance.plutus.model.partner.dto.PartnerDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface FindPartnerService {
	List<PartnerDto> findAll();
}
