package com.finance.plutus.old.service.serial;

import com.finance.plutus.invoice.model.Serial;

/** Plutus Created by catalin on 9/7/2020 */
public interface IncreaseSerialService {
  String getNextName(Serial serial);

  String getDraftName(Serial serial);
}
