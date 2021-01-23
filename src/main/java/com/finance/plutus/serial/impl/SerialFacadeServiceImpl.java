package com.finance.plutus.serial.impl;

import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.serial.Serial;
import com.finance.plutus.serial.dto.SerialDto;
import com.finance.plutus.serial.SerialFacadeService;
import com.finance.plutus.serial.SerialMapper;
import com.finance.plutus.serial.SerialService;
import com.finance.plutus.serial.dto.UpdateSerialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
@Service
@RequiredArgsConstructor
public class SerialFacadeServiceImpl implements SerialFacadeService {

  private final SerialMapper serialMapper;
  private final SerialService serialService;

  @Override
  public PlutusResponse<SerialDto> findById(UUID id) {
    Serial serial = serialService.findById(id);
    SerialDto serialDto = serialMapper.mapToDto(serial);
    return new PlutusResponse<>(serialDto);
  }

  @Override
  public void update(UUID id, PlutusRequest<UpdateSerialDto> request) {
    serialService.update(id, request.getData());
  }
}
