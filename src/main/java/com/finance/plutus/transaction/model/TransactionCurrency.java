package com.finance.plutus.transaction.model;

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

/** Plutus Created by Catalin on 12/21/2020 */
@Getter
@Setter
@Entity
@Table(name = "transaction_currencies")
public class TransactionCurrency {

  @Id @GeneratedValue private UUID id;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Column(name = "currency", nullable = false)
  private Currency currency;

  @NotNull
  @Column(name = "value", nullable = false)
  private Double value;

  @NotNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NotNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
