package com.finance.plutus.service.serial.impl;

import com.finance.plutus.exception.SerialException;
import com.finance.plutus.model.serial.Serial;
import com.finance.plutus.repository.serial.SerialRepository;
import com.finance.plutus.service.serial.UpdateSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.01.2020
 */
@Service
@RequiredArgsConstructor
public class UpdateSerialServiceImpl implements UpdateSerialService {

    private final SerialRepository serialRepository;

    @Override
    public String createSerialNumber(Serial serial) {
        long current = serial.getNumber();
        if (current == serial.getNumber()) {
            throw SerialException.serialNumberOverflow();
        }
        String serialNumber = serial.getName() + current;
        serial.setNumber(++current);
        serialRepository.save(serial);
        return serialNumber;
    }

}
