package com.finance.plutus.old.model.html;

import com.finance.plutus.bank.model.Bank;
import com.finance.plutus.old.model.entity.Business;
import com.finance.plutus.country.model.Country;
import com.finance.plutus.old.model.entity.Currency;
import com.finance.plutus.old.model.entity.Invoice;
import com.finance.plutus.old.model.entity.InvoiceLine;
import com.finance.plutus.old.model.entity.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
  private String vatInVies;
  private String commercialRegistry;
  private String address;
  private String email;
  private String phone;
  private String website;
  private String bankName;
  private String bankAccount;
  private String clientName;
  private String clientVat;
  private String clientCommercialRegistry;
  private String clientAddress;
  private String clientCountry;
  private String clientEmail;
  private String clientPhone;
  private String clientBankName;
  private String clientBankAccount;
  private List<Line> lines;
  private String total;
  private boolean hasCurrency;

  public String getName() {
    return name;
  }

  public static Params set(Invoice invoice, Business business) {
    Partner client = invoice.getClient();
    return Params.builder()
        .name(invoice.getName())
        .date(invoice.getDate().toString())
        .dueDate(invoice.getDueDate().toString())
        .businessName(business.getName())
        .vat(business.getVat())
        .vatInVies(business.getVies())
        .commercialRegistry(business.getCommercialRegistry())
        .address(business.getAddress())
        .email(business.getEmail())
        .phone(business.getPhone())
        .website(business.getWebsite())
        .bankName(business.getBank().getName())
        .bankAccount(business.getBankAccount())
        .clientName(client.getName())
        .clientVat(client.getVat())
        .clientCommercialRegistry(client.getCommercialRegistry())
        .clientAddress(client.getAddress())
        .clientCountry(Optional.ofNullable(client.getCountry()).map(Country::getName).orElse(null))
        .clientEmail(client.getEmail())
        .clientPhone(client.getPhone())
        .clientBankName(Optional.ofNullable(client.getBank()).map(Bank::getName).orElse(null))
        .clientBankAccount(client.getBankAccount())
        .total(String.format("%.2f LEI", invoice.getTotal()))
        .hasCurrency(invoice.getCurrency() != Currency.RON)
        .lines(invoice.getLines().stream().map(Params::mapLine).collect(Collectors.toList()))
        .build();
  }

  private static Line mapLine(InvoiceLine invoiceLine) {
    return Line.builder()
        .name(invoiceLine.getItem().getName())
        .quantity(invoiceLine.getQuantity())
        .price(String.format("%.2f", invoiceLine.getUnitPrice()))
        .total(String.format("%.2f", invoiceLine.getTotal()))
        .currencyAmount(
            String.format(
                "%.2f %s", invoiceLine.getCurrencyTotal(), invoiceLine.getCurrency().name()))
        .currencyRate(String.format("Curs: %.2f", invoiceLine.getCurrencyRate()))
        .build();
  }

  public Map<String, Object> getMap() {
    Map<String, Object> paramsMap = new HashMap<>();
    paramsMap.put("name", name);
    paramsMap.put("date", date);
    paramsMap.put("due_date", dueDate);
    paramsMap.put("business_name", businessName);
    paramsMap.put("vat", vat);
    paramsMap.put("vat_in_vies", vatInVies);
    paramsMap.put("commercial_registry", commercialRegistry);
    paramsMap.put("address", address);
    paramsMap.put("email", email);
    paramsMap.put("phone", phone);
    paramsMap.put("website", website);
    paramsMap.put("bank_name", bankName);
    paramsMap.put("bank_account", bankAccount);
    paramsMap.put("client_name", clientName);
    paramsMap.put("client_vat", clientVat);
    paramsMap.put("client_commercial_registry", clientCommercialRegistry);
    paramsMap.put("client_address", clientAddress);
    paramsMap.put("client_country", clientCountry);
    paramsMap.put("client_email", clientEmail);
    paramsMap.put("client_phone", clientPhone);
    paramsMap.put("client_bank_name", clientBankName);
    paramsMap.put("client_bank_account", clientBankAccount);
    paramsMap.put("lines", lines);
    paramsMap.put("total", total);
    paramsMap.put("has_currency", hasCurrency);
    return paramsMap;
  }
}
