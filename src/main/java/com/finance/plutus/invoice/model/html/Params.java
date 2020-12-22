package com.finance.plutus.invoice.model.html;

import com.finance.plutus.invoice.model.Invoice;
import com.finance.plutus.invoice.model.InvoiceLine;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.user.model.Business;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 9/7/2020 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Params {

  private String name;
  private String date;
  private String dueDate;
  private String businessName;
  private String vat;
  private String commercialRegistry;
  private String address;
  private String email;
  private String phone;
  private String bankName;
  private String bankAccount;
  private String clientName;
  private String clientAddress;
  private String clientEmail;
  private List<Line> lines;
  private String total;
  private String subtotal;

  public String getName() {
    return name;
  }

  public static Params set(Invoice invoice, Business business) {
    Partner client = invoice.getClient();
    return Params.builder()
        .name(String.format("Factura %s", invoice.getName()))
        .date(String.format("Data: %s", invoice.getDate().toString()))
        .dueDate(
            Optional.ofNullable(invoice.getDueDate())
                .map(d -> String.format("Data scadenta: %s", d.toString()))
                .orElse(null))
        .businessName(business.getName())
        .vat(String.format("CUI: %s", business.getVat()))
        .commercialRegistry(String.format("Reg Com: %s", business.getCommercialRegistry()))
        .address(business.getAddress())
        .email(business.getEmail())
        .phone(business.getPhone())
        .bankName(business.getBank().getName())
        .bankAccount(business.getBankAccount())
        .clientName(client.getName())
        .clientAddress(client.getAddress())
        .clientEmail(client.getEmail())
        .subtotal(String.format("%.2f RON", invoice.getSubtotal()))
        .total(String.format("%.2f RON", invoice.getTotal()))
        .lines(createLines(new ArrayList<>(invoice.getLines())))
        .build();
  }

  private static List<Line> createLines(List<InvoiceLine> lines) {
    return lines.stream()
        .map(line -> mapLine(line, lines.indexOf(line) + 1))
        .collect(Collectors.toList());
  }

  private static Line mapLine(InvoiceLine invoiceLine, int index) {
    return Line.builder()
        .number(String.format("0%d", index))
        .name(invoiceLine.getItem().getName())
        .details(null)
        .quantity(invoiceLine.getQuantity())
        .price(String.format("%.2f RON", invoiceLine.getUnitPrice()))
        .total(String.format("%.2f RON", invoiceLine.getTotal()))
        .build();
  }

  public Map<String, Object> getMap() {
    Map<String, Object> paramsMap = new HashMap<>();
    paramsMap.put("invoice_name", name);
    paramsMap.put("invoice_date", date);
    paramsMap.put("invoice_due_date", dueDate);
    paramsMap.put("invoice_subtotal", subtotal);
    paramsMap.put("invoice_total", total);
    paramsMap.put("invoice_lines", lines);

    paramsMap.put("business_name", businessName);
    paramsMap.put("business_address", address);
    paramsMap.put("business_phone", phone);
    paramsMap.put("business_email", email);
    paramsMap.put("business_vat", vat);
    paramsMap.put("business_commercial_registry", commercialRegistry);
    paramsMap.put("business_bank", bankName);
    paramsMap.put("business_bank_account", bankAccount);

    paramsMap.put("client_name", clientName);
    paramsMap.put("client_address", clientAddress);
    paramsMap.put("client_email", clientEmail);

    return paramsMap;
  }
}
