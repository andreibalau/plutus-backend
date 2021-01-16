package com.finance.plutus.invoice.model.html;

import com.finance.plutus.app.service.Params;
import com.finance.plutus.app.util.DateFormatter;
import com.finance.plutus.currency.model.Currency;
import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.model.InvoiceCurrency;
import com.finance.plutus.invoice.model.InvoiceLine;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.user.model.Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 1/16/2021 */
public class InvoiceParams implements Params<Invoice> {

  private final Map<String, Object> params = new HashMap<>();

  @Override
  public String getName() {
    return params.get("name").toString();
  }

  @Override
  public Map<String, Object> getMap() {
    return params;
  }

  @Override
  public void submit(Invoice invoice) {
    params.put("name", String.format("Factura %s", invoice.getName()));
    params.put("date", String.format("Data: %s", DateFormatter.formatDate(invoice.getDate())));
    params.put(
        "due_date",
        Optional.ofNullable(invoice.getDueDate())
            .map(d -> String.format("Data scadenta: %s", DateFormatter.formatDate(d)))
            .orElse(null));
    params.put("subtotal", String.format("%.2f RON", invoice.getSubtotal()));
    params.put("total", String.format("%.2f RON", invoice.getTotal()));
    params.put("lines", createLines(new ArrayList<>(invoice.getLines())));
    Partner client = invoice.getClient();
    params.put("client_name", client.getName());
    params.put("client_address", client.getAddress());
    params.put("client_email", client.getEmail());
  }

  public void submitBusiness(Business business) {
    params.put("business_name", business.getName());
    params.put("business_address", business.getAddress());
    params.put("business_phone", business.getPhone());
    params.put("business_email", business.getEmail());
    params.put("business_vat", business.getVat());
    params.put("business_commercial_registry", business.getCommercialRegistry());
    params.put("business_bank", business.getBank().getName());
    params.put("business_bank_account", business.getBankAccount());
  }

  private List<Line> createLines(List<InvoiceLine> lines) {
    return lines.stream()
        .map(line -> mapLine(line, lines.indexOf(line) + 1))
        .collect(Collectors.toList());
  }

  private Line mapLine(InvoiceLine invoiceLine, int index) {
    String details = null;
    InvoiceCurrency invoiceCurrency = invoiceLine.getCurrency();
    if (invoiceCurrency != null) {
      Currency currency = invoiceCurrency.getValue();
      double total = invoiceCurrency.getTotal();
      double rate = invoiceCurrency.getRate();
      details = String.format("%.2f %s; Curs: %.4f", total, currency.name(), rate);
    }
    return Line.builder()
        .number(String.format("0%d", index))
        .name(invoiceLine.getItem().getName())
        .details(details)
        .quantity(invoiceLine.getQuantity())
        .price(String.format("%.2f RON", invoiceLine.getUnitPrice()))
        .total(String.format("%.2f RON", invoiceLine.getTotal()))
        .build();
  }
}
