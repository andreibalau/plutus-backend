package com.finance.plutus.item.dto;

import com.finance.plutus.item.ItemType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class ItemDto {
  private String id;
  private String name;
  private String code;
  private String description;
  private Double unitPrice;
  private Double vat;
  private Double totalPrice;
  private ItemType type;
  private String uom;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
