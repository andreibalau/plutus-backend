package com.finance.plutus.invoice;

import com.finance.plutus.app.BaseModel;
import com.finance.plutus.item.Item;
import com.finance.plutus.item.ItemVat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.CascadeType;
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
import java.util.UUID;

/** Plutus Created by catalin on 21.09.2019 */
@Getter
@Setter
@Entity
@Table(name = "invoice_lines")
public class InvoiceLine extends BaseModel {

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
  @Column(name = "details")
  private String details;

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
  @Enumerated(EnumType.STRING)
  @Column(name = "vat", nullable = false)
  private ItemVat vat;

  @NotNull
  @Column(name = "total", nullable = false)
  private Double total;

  @Nullable
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "invoice_currency_id")
  private InvoiceCurrency currency;
}
