package com.finance.plutus.transaction.model;

import com.finance.plutus.partner.Partner;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/** Plutus Created by Catalin on 10/31/2020 */
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

  @Id @GeneratedValue private UUID id;

  @NotNull
  @Column(name = "date", nullable = false)
  private LocalDate date;

  @NotBlank
  @Column(name = "document", nullable = false)
  private String document;

  @NotBlank
  @Column(name = "details", nullable = false)
  private String details;

  @NotNull
  @Column(name = "value", nullable = false)
  private Double value;

  @NotNull
  @Column(name = "deductible", nullable = false)
  private Boolean deductible;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "partner_id", nullable = false)
  private Partner partner;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_type", nullable = false)
  private TransactionType type;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_method", nullable = false)
  private TransactionMethod method;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_status", nullable = false)
  private TransactionStatus status;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "transaction_currency_id")
  private TransactionCurrency transactionCurrency;

  @NotNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NotNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
