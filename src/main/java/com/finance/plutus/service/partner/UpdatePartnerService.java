package com.finance.plutus.service.partner;

import com.finance.plutus.model.partner.dto.ModifyPartnerDto;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
public interface UpdatePartnerService {
	void update(Long partnerId, ModifyPartnerDto partnerDto);
}
