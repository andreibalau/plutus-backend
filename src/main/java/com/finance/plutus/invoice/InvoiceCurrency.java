package com.finance.plutus.invoice;

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

/** Plutus Created by Catalin on 10/31/2020 */
@Getter
@Setter
@Entity
@Table(name = "invoice_currencies")
public class InvoiceCurrency extends BaseModel {

  @Id @GeneratedValue private UUID id;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "value", nullable = false)
  private Currency value = Currency.USD;

  @NotNull
  @Column(name = "rate", nullable = false)
  private Double rate;

  @NotNull
  @Column(name = "subtotal", nullable = false)
  private Double subtotal;

  @NotNull
  @Column(name = "total", nullable = false)
  private Double total;
}
