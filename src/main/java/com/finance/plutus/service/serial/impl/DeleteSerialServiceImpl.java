package com.finance.plutus.service.serial.impl;

import com.finance.plutus.exception.SerialException;
import com.finance.plutus.model.serial.Serial;
import com.finance.plutus.repository.serial.SerialRepository;
import com.finance.plutus.service.serial.DeleteSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/30/2020
 */
@Service
@RequiredArgsConstructor
public class DeleteSerialServiceImpl implements DeleteSerialService {

	private final SerialRepository serialRepository;

	@Override
	public void delete(Long serialId) {
		Serial serial = findSerial(serialId);
		serialRepository.delete(serial);
	}

	private Serial findSerial(Long serialId) {
		return serialRepository
				.findById(serialId)
				.orElseThrow(SerialException::serialNotFound);
	}

}
