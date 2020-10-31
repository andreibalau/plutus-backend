package com.finance.plutus.item.model;

import com.finance.plutus.item.model.ItemType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/** Plutus Created by Catalin on 8/7/2020 */
@Getter
@Setter
public class UpdateItemDto {
  @NotBlank private String name;
  @NotNull private Double unitPrice;
  @NotNull private Double vat;
  @NotNull private ItemType type;
  private String uom;
  private String code;
  private String description;
}
