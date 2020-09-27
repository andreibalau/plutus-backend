package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
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

  @Id @NonNull private String id;

  @NonNull
  @Column(name = "serial_name", nullable = false, unique = true)
  private String name;

  @NonNull
  @Column(name = "date", nullable = false)
  private LocalDate date;

  @NonNull
  @Column(name = "due_date", nullable = false)
  private LocalDate dueDate;

  @NonNull
  @Column(name = "subtotal", nullable = false)
  private Double subtotal;

  @NonNull
  @Column(name = "taxes", nullable = false)
  private Double taxes;

  @NonNull
  @Column(name = "total", nullable = false)
  private Double total;

  @NonNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private InvoiceStatus status = InvoiceStatus.DRAFT;

  @NonNull
  @Enumerated(EnumType.STRING)
  @Column(name = "currency", nullable = false)
  private Currency currency = Currency.RON;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "serial_id", nullable = false)
  private Serial serial;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private Partner client;

  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<InvoiceLine> lines = new HashSet<>();

  @NonNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NonNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
