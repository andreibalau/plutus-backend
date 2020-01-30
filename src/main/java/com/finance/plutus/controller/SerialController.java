package com.finance.plutus.controller;

import java.util.List;

import com.finance.plutus.api.SerialApi;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.serial.dto.CreateSerialDto;
import com.finance.plutus.model.serial.dto.SerialDto;
import com.finance.plutus.service.serial.CreateSerialService;
import com.finance.plutus.service.serial.FindSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 1/30/2020
 */
@RestController
@RequiredArgsConstructor
public class SerialController implements SerialApi {

	private final FindSerialService findSerialService;
	private final CreateSerialService createSerialService;

	@Override
	public List<SerialDto> findAll() {
		return findSerialService.findAll();
	}

	@Override
	public EntityCreatedDto create(CreateSerialDto createSerialDto) {
		return createSerialService.create(createSerialDto);
	}

	@Override
	public void delete(Long serialId) {

	}

}
