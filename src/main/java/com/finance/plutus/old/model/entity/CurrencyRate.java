package com.finance.plutus.old.model.entity;

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
import java.time.LocalDate;
import java.util.UUID;

/** Plutus Created by Catalin on 10/4/2020 */
@Getter
@Setter
@Entity
@Table(name = "currency_rates")
public class CurrencyRate {

  @Id @GeneratedValue private UUID id;

  @NotNull
  @Column(name = "date", nullable = false)
  private LocalDate date;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "currency", nullable = false)
  private Currency currency;

  @NotNull
  @Column(name = "rate", nullable = false)
  private Double rate;
}
