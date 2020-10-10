package com.finance.plutus.service.item.impl;

import com.finance.plutus.model.dto.CreateItemDto;
import com.finance.plutus.model.entity.Item;
import com.finance.plutus.model.entity.ItemVat;
import com.finance.plutus.repository.ItemRepository;
import com.finance.plutus.service.item.CreateItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class CreateItemServiceImpl implements CreateItemService {

  private final ItemRepository itemRepository;

  @Override
  @Transactional
  public String create(CreateItemDto createItemDto) {
    Item item = createItem(createItemDto);
    itemRepository.save(item);
    return item.getId().toString();
  }

  public Item createItem(CreateItemDto createItemDto) {
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
}
