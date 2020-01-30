package com.finance.plutus.service.serial.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.model.serial.dto.SerialDto;
import com.finance.plutus.repository.serial.SerialRepository;
import com.finance.plutus.service.serial.FindSerialService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/30/2020
 */
@Service
@RequiredArgsConstructor
public class FindSerialServiceImpl implements FindSerialService {

	private final SerialRepository serialRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<SerialDto> findAll() {
		return serialRepository
				.findAll()
				.stream()
				.map(serial -> modelMapper.map(serial, SerialDto.class))
				.collect(Collectors.toList());
	}
}
