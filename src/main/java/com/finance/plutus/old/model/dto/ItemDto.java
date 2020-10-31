package com.finance.plutus.old.model.dto;

import com.finance.plutus.old.model.entity.Item;
import com.finance.plutus.old.model.entity.ItemType;
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

  public static ItemDto mapFromEntity(Item item) {
    ItemDto itemDto = new ItemDto();
    itemDto.setId(item.getId().toString());
    itemDto.setCreatedOn(item.getCreatedOn());
    itemDto.setUpdatedOn(item.getUpdatedOn());
    itemDto.setType(item.getType());
    itemDto.setName(item.getName());
    itemDto.setDescription(item.getDescription());
    itemDto.setTotalPrice(item.getTotalPrice());
    itemDto.setUnitPrice(item.getUnitPrice());
    itemDto.setUom(item.getUom());
    itemDto.setVat(item.getVat().getAmountPercent());
    itemDto.setCode(item.getCode());
    return itemDto;
  }
}
