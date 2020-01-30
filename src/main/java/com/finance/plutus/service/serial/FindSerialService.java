package com.finance.plutus.service.serial;

import java.util.List;

import com.finance.plutus.model.serial.dto.SerialDto;

/**
 * Plutus
 * Created by catalin on 1/30/2020
 */
public interface FindSerialService {
	List<SerialDto> findAll();
}
