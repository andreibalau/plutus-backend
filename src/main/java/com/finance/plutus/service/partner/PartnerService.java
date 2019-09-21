package com.finance.plutus.service.partner;

import com.finance.plutus.model.partner.dto.CreatePartnerDto;
import com.finance.plutus.model.partner.dto.PartnerDto;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface PartnerService {
	void create(CreatePartnerDto createPartnerDto);
	List<PartnerDto> find();
}