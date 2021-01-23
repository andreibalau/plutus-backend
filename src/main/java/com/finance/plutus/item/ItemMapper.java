package com.finance.plutus.item;

import com.finance.plutus.item.dto.CreateItemDto;
import com.finance.plutus.item.dto.ItemDto;
import com.finance.plutus.item.dto.UpdateItemDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by Catalin on 1/23/2021 */
@Component
public class ItemMapper {

  public ItemDto mapToDto(Item item) {
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

  public Item mapToEntity(CreateItemDto createItemDto) {
    Item item = new Item();
    item.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    item.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    item.setType(createItemDto.getType());
    item.setName(createItemDto.getName());
    item.setDescription(createItemDto.getDescription());
    item.setUnitPrice(createItemDto.getUnitPrice());
    item.setVat(ItemVat.fromAmount(createItemDto.getVat()));
    item.setUom(createItemDto.getUom());
    item.setCode(createItemDto.getCode());
    item.setTotalPrice(item.getVat().getAmount() * item.getUnitPrice() + item.getUnitPrice());
    return item;
  }

  public Item mapToEntity(Item item, UpdateItemDto updateItemDto) {
    item.setType(updateItemDto.getType());
    item.setName(updateItemDto.getName());
    item.setDescription(updateItemDto.getDescription());
    item.setUnitPrice(updateItemDto.getUnitPrice());
    item.setVat(ItemVat.fromAmount(updateItemDto.getVat()));
    item.setUom(updateItemDto.getUom());
    item.setCode(updateItemDto.getCode());
    item.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    item.setTotalPrice(item.getVat().getAmount() * item.getUnitPrice() + item.getUnitPrice());
    return item;
  }
}
