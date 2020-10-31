package com.finance.plutus.invoice.model;

import com.finance.plutus.currency.model.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
@Getter
@Setter
@Entity
@Table(name = "invoice_currencies")
public class InvoiceCurrency {

  @Id @GeneratedValue private UUID id;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "currency", nullable = false)
  private Currency currency = Currency.USD;

  @NotNull
  @Column(name = "rate", nullable = false)
  private Double rate;

  @NotNull
  @Column(name = "total", nullable = false)
  private Double total;

  @NotNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NotNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
