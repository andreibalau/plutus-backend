package com.finance.plutus.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.entity.ItemType;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 8/7/2020 */
@Getter
@Setter
public class UpdateItemDto {
  @NotBlank private String name;
  @NotNull private Double unitPrice;
  @NotNull private Double vat;
  @NotNull private ItemType type;
  private String uom;
}
