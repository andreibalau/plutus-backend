package com.finance.plutus.service.partner;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.partner.dto.CreatePartnerDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface CreatePartner {
	EntityCreatedDto create(CreatePartnerDto createPartnerDto);
}
