package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.ItemType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class CreateItemDto {
  @NotBlank private String name;
  @NotNull private Double unitPrice;
  @NotNull private Double vat;
  @NotNull private ItemType type;
  private String uom;
}