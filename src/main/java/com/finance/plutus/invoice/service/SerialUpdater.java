package com.finance.plutus.invoice.service;

import com.finance.plutus.invoice.model.Serial;

/** Plutus Created by Catalin on 11/1/2020 */
public interface SerialUpdater {
  String increment(Serial serial);

  void decrement(Serial serial, int steps);
}
