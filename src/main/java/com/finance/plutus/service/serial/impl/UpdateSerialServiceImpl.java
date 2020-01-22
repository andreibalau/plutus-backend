package com.finance.plutus.service.serial.impl;

import com.finance.plutus.model.serial.Serial;
import com.finance.plutus.repository.invoice.SerialRepository;
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
    public void update(Serial serial) {
        serialRepository.save(serial);
    }

}
