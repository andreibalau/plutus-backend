package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice {

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(nullable = false, name = "created_on")
  private LocalDateTime createdOn;

  @NotNull
  @Column(nullable = false, name = "updated_on")
  private LocalDateTime updatedOn;

  @NotNull
  @ManyToOne
  @JoinColumn(nullable = false)
  private Partner partner;

  @NotBlank
  @Column(nullable = false, unique = true, name = "serial_name")
  private String name;

  @NotNull
  @Column(nullable = false, name = "date")
  private LocalDate date;

  @NotNull
  @Column(nullable = false, name = "due_date")
  private LocalDate dueDate;

  @NotNull
  @Column(nullable = false, name = "subtotal")
  private Double subtotal;

  @NotNull
  @Column(nullable = false, name = "taxes")
  private Double taxes;

  @NotNull
  @Column(nullable = false, name = "total")
  private Double total;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "invoice_type")
  private InvoiceType type;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "status")
  private InvoiceStatus status = InvoiceStatus.DRAFT;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "currency")
  private Currency currency = Currency.RON;

  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<InvoiceLine> lines = new HashSet<>();
}
