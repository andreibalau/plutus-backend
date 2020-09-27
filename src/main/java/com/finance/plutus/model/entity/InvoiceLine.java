package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

/** Plutus Created by catalin on 21.09.2019 */
@Getter
@Setter
@Entity
@Table(name = "invoice_lines")
public class InvoiceLine {

  @Id @NonNull private String id;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "invoice_id", nullable = false)
  private Invoice invoice;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @Nullable
  @Column(name = "uom")
  private String uom;

  @NonNull
  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @NonNull
  @Column(name = "unit_price", nullable = false)
  private Double unitPrice;

  @NonNull
  @Column(name = "subtotal", nullable = false)
  private Double subtotal;

  @NonNull
  @Column(name = "vat", nullable = false)
  private ItemVat vat;

  @NonNull
  @Column(name = "total", nullable = false)
  private Double total;

  @NonNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NonNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
