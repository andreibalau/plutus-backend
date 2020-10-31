package com.finance.plutus.old.service.item.impl;

import com.finance.plutus.old.exception.EntityNotFoundException;
import com.finance.plutus.old.model.dto.ItemDto;
import com.finance.plutus.old.model.entity.Item;
import com.finance.plutus.old.repository.ItemRepository;
import com.finance.plutus.old.service.item.FindItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class FindItemServiceImpl implements FindItemService {

  private final ItemRepository itemRepository;

  @Override
  public ItemDto findDtoById(UUID id) {
    Item item = findEntityById(id);
    return ItemDto.mapFromEntity(item);
  }

  @Override
  public Item findEntityById(UUID id) {
    return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("item"));
  }

  @Override
  public List<ItemDto> findAllDtoByPage(int page, int size) {
    return itemRepository.findAll(PageRequest.of(page, size)).stream()
        .map(ItemDto::mapFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public long countAll() {
    return (int) itemRepository.count();
  }
}
