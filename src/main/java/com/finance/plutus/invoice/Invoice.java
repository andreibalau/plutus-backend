package com.finance.plutus.invoice;

import com.finance.plutus.app.BaseModel;
import com.finance.plutus.partner.Partner;
import com.finance.plutus.serial.Serial;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice extends BaseModel {

  @Id @GeneratedValue private UUID id;

  @NotBlank
  @Column(name = "serial_name", nullable = false, unique = true)
  private String name;

  @NotNull
  @Column(name = "date", nullable = false)
  private LocalDate date;

  @Nullable
  @Column(name = "due_date")
  private LocalDate dueDate;

  @NotNull
  @Column(name = "subtotal", nullable = false)
  private Double subtotal;

  @NotNull
  @Column(name = "taxes", nullable = false)
  private Double taxes;

  @NotNull
  @Column(name = "total", nullable = false)
  private Double total;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private InvoiceStatus status = InvoiceStatus.DRAFT;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "serial_id", nullable = false)
  private Serial serial;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private Partner customer;

  @Nullable
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "invoice_currency_id")
  private InvoiceCurrency currency;

  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<InvoiceLine> lines = new HashSet<>();
}
