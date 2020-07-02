package com.finance.plutus.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(nullable = false, name = "created_on")
  private LocalDateTime createdOn;

  @NotNull
  @Column(nullable = false, name = "updated_on")
  private LocalDateTime updatedOn;

  @NotBlank
  @Column(nullable = false, name = "name")
  private String name;

  @Column(name = "uom")
  private String uom;

  @NotNull
  @Column(nullable = false, name = "unit_price")
  private Double unitPrice;

  @NotNull
  @Column(nullable = false, name = "vat")
  private Double vat;

  @NotNull
  @Column(nullable = false, name = "total_price")
  private Double totalPrice;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "type")
  private ItemType itemType = ItemType.PRODUCT;
}
