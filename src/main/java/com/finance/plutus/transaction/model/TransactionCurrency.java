package com.finance.plutus.transaction.model;

import com.finance.plutus.app.BaseModel;
import com.finance.plutus.currency.Currency;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/** Plutus Created by Catalin on 12/21/2020 */
@Getter
@Setter
@Entity
@Table(name = "transaction_currencies")
public class TransactionCurrency extends BaseModel {

  @Id @GeneratedValue private UUID id;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Column(name = "currency", nullable = false)
  private Currency currency;

  @NotNull
  @Column(name = "value", nullable = false)
  private Double value;

  @Nullable
  @Column(name = "rate")
  private Double rate;
}
