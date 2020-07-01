package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/** Plutus Created by catalin on 21.09.2019 */
@Getter
@Setter
@Entity
@Table(name = "invoice_lines")
public class InvoiceLine {

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(nullable = false, name = "created_on")
  private LocalDateTime createdOn;

  @NotNull
  @Column(nullable = false, name = "updated_on")
  private LocalDateTime updatedOn;

  @NotNull
  @ManyToOne
  @JoinColumn(nullable = false, name = "invoice_id")
  private Invoice invoice;

  @NotNull
  @ManyToOne
  @JoinColumn(nullable = false, name = "item_id")
  private Item item;

  @Column(name = "uom")
  private String uom;

  @NotNull
  @Column(nullable = false, name = "quantity")
  private Double quantity;

  @NotNull
  @Column(nullable = false, name = "price")
  private Double price;

  @NotNull
  @Column(nullable = false, name = "subtotal")
  private Double subtotal;

  @NotNull
  @Column(nullable = false, name = "taxes")
  private Double taxes;

  @NotNull
  @Column(nullable = false, name = "total")
  private Double total;
}
