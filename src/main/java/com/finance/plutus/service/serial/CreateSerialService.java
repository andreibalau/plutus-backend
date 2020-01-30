package com.finance.plutus.service.serial;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.serial.dto.CreateSerialDto;

/**
 * Plutus
 * Created by catalin on 1/30/2020
 */
public interface CreateSerialService {
	EntityCreatedDto create(CreateSerialDto createSerialDto);
}
