package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {

  @Id @NonNull private String id;

  @NonNull
  @Column(name = "name", nullable = false)
  private String name;

  @Nullable
  @Column(name = "description")
  private String description;

  @Nullable
  @Column(name = "code")
  private String code;

  @Nullable
  @Column(name = "uom")
  private String uom;

  @NonNull
  @Column(name = "unit_price", nullable = false)
  private Double unitPrice;

  @NonNull
  @Enumerated(value = EnumType.STRING)
  @Column(name = "item_vat", nullable = false)
  private ItemVat vat;

  @NonNull
  @Column(name = "total_price", nullable = false)
  private Double totalPrice;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Column(name = "type", nullable = false)
  private ItemType type = ItemType.PRODUCT;

  @NonNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NonNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
