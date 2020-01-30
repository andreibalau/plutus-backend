package com.finance.plutus.service.serial.impl;

import com.finance.plutus.exception.SerialException;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.serial.Serial;
import com.finance.plutus.model.serial.dto.CreateSerialDto;
import com.finance.plutus.repository.serial.SerialRepository;
import com.finance.plutus.service.serial.CreateSerialService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/30/2020
 */
@Service
@RequiredArgsConstructor
public class CreateSerialServiceImpl implements CreateSerialService {

	private final SerialRepository serialRepository;
	private final ModelMapper modelMapper;

	@Override
	public EntityCreatedDto create(CreateSerialDto createSerialDto) {
		checkIfSerialExists(createSerialDto.getName());
		Serial serial = modelMapper.map(createSerialDto, Serial.class);
		serial.setNumber(serial.getMin());
		serial.setCreatedOn(System.currentTimeMillis());
		serial.setUpdatedOn(System.currentTimeMillis());
		return new EntityCreatedDto(serialRepository.save(serial).getId());
	}

	private void checkIfSerialExists(String name) {
		if (serialRepository.findByName(name).isPresent()) {
			throw SerialException.serialAlreadyExists();
		}
	}

}
