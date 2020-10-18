package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/** Plutus Created by catalin on 21.09.2019 */
@Getter
@Setter
@Entity
@Table(name = "invoice_lines")
public class InvoiceLine {

  @Id @GeneratedValue private UUID id;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "invoice_id", nullable = false)
  private Invoice invoice;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @Nullable
  @Column(name = "uom")
  private String uom;

  @NotNull
  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @NotNull
  @Column(name = "unit_price", nullable = false)
  private Double unitPrice;

  @NotNull
  @Column(name = "subtotal", nullable = false)
  private Double subtotal;

  @NotNull
  @Column(name = "vat", nullable = false)
  private ItemVat vat;

  @NotNull
  @Column(name = "total", nullable = false)
  private Double total;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "currency", nullable = false)
  private Currency currency = Currency.RON;

  @NotNull
  @Column(name = "currency_rate", nullable = false)
  private Double currencyRate;

  @NotNull
  @Column(name = "currency_unit_price", nullable = false)
  private Double currencyUnitPrice;

  @NotNull
  @Column(name = "currency_subtotal", nullable = false)
  private Double currencySubtotal;

  @NotNull
  @Column(name = "currency_total", nullable = false)
  private Double currencyTotal;

  @NotNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NotNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
