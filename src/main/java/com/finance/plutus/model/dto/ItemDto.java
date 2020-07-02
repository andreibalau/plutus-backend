package com.finance.plutus.model.dto;

import java.time.LocalDateTime;

import com.finance.plutus.model.entity.ItemType;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class ItemDto {
  private Long id;
  private String name;
  private Double unitPrice;
  private Double vat;
  private Double totalPrice;
  private ItemType itemType;
  private String uom;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
