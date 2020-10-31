package com.finance.plutus.old.service.invoice;

import com.finance.plutus.old.model.dto.InvoiceCommand;
import com.finance.plutus.old.model.entity.Invoice;
import com.finance.plutus.old.service.invoice.impl.ActivateInvoiceService;
import com.finance.plutus.old.service.invoice.impl.CancelInvoiceService;
import com.finance.plutus.old.service.invoice.impl.CompleteInvoiceService;
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
      CompleteInvoiceService completeInvoiceService) {
    commands.put(InvoiceCommand.ACTIVATE, activateInvoiceService);
    commands.put(InvoiceCommand.CANCEL, cancelInvoiceService);
    commands.put(InvoiceCommand.COMPLETE, completeInvoiceService);
  }

  public void invoke(UUID id, InvoiceCommand command) {
    InvoiceExecutableCommand invoiceExecutableCommand = getExecutableCommand(command);
    invoiceExecutableCommand.execute(id);
  }

  public void invoke(Invoice invoice, InvoiceCommand command) {
    InvoiceExecutableCommand invoiceExecutableCommand = getExecutableCommand(command);
    invoiceExecutableCommand.execute(invoice);
  }

  private InvoiceExecutableCommand getExecutableCommand(InvoiceCommand command) {
    return commands.get(command);
  }
}
