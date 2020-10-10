package com.finance.plutus.service.invoice;

import com.finance.plutus.model.dto.InvoiceCommand;
import com.finance.plutus.service.invoice.impl.ActivateInvoiceService;
import com.finance.plutus.service.invoice.impl.CancelInvoiceService;
import com.finance.plutus.service.invoice.impl.CompleteInvoiceService;
import com.finance.plutus.service.invoice.impl.EnableInvoiceService;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
@Service
public class InvoiceCommandInvoker {

  private final Map<InvoiceCommand, InvoiceExecutableCommand> commands =
      new EnumMap<>(InvoiceCommand.class);

  public InvoiceCommandInvoker(
      ActivateInvoiceService activateInvoiceService,
      CancelInvoiceService cancelInvoiceService,
      CompleteInvoiceService completeInvoiceService,
      EnableInvoiceService enableInvoiceService) {
    commands.put(InvoiceCommand.ACTIVATE, activateInvoiceService);
    commands.put(InvoiceCommand.CANCEL, cancelInvoiceService);
    commands.put(InvoiceCommand.COMPLETE, completeInvoiceService);
    commands.put(InvoiceCommand.ENABLE, enableInvoiceService);
  }

  public void invoke(UUID id, InvoiceCommand command) {
    InvoiceExecutableCommand invoiceExecutableCommand = getExecutableCommand(command);
    invoiceExecutableCommand.execute(id);
  }

  private InvoiceExecutableCommand getExecutableCommand(InvoiceCommand command) {
    return commands.get(command);
  }
}
