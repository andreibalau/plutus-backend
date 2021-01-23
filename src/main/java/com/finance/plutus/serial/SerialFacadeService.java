package com.finance.plutus.serial;

import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.serial.dto.SerialDto;
import com.finance.plutus.serial.dto.UpdateSerialDto;

import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
public interface SerialFacadeService {
  PlutusResponse<SerialDto> findById(UUID id);

  void update(UUID id, PlutusRequest<UpdateSerialDto> request);
}
