package com.finance.plutus.service.invoice;

import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
public interface InvoiceExecutableCommand {
  void execute(UUID id);
}
