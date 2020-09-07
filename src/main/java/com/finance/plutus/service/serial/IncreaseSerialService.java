package com.finance.plutus.service.serial;

import com.finance.plutus.model.entity.Serial;

/** Plutus Created by catalin on 9/7/2020 */
public interface IncreaseSerialService {
  String getNextName(Serial serial);
}
