package com.finance.plutus.service.item.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.ItemDto;
import com.finance.plutus.model.dto.PreviewItemDto;
import com.finance.plutus.model.entity.Item;
import com.finance.plutus.repository.ItemRepository;
import com.finance.plutus.service.item.FindItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class FindItemServiceImpl implements FindItemService {

  private final ItemRepository itemRepository;

  @Override
  public ItemDto findDtoById(Long id) {
    Item item = findById(id);
    return map(item);
  }

  @Override
  public Item findById(Long id) {
    return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("item"));
  }

  @Override
  public List<PreviewItemDto> findAllByPage(int page, int size) {
    return itemRepository.findAll(PageRequest.of(page, size)).stream()
        .map(i -> new PreviewItemDto(i.getId(), i.getName(), i.getUnitPrice()))
        .collect(Collectors.toList());
  }

  @Override
  public List<PreviewItemDto> findAll() {
    return itemRepository.findAll().stream()
        .map(i -> new PreviewItemDto(i.getId(), i.getName(), i.getUnitPrice()))
        .collect(Collectors.toList());
  }

  @Override
  public int countAll() {
    return (int) itemRepository.count();
  }

  private ItemDto map(Item item) {
    ItemDto itemDto = new ItemDto();
    itemDto.setId(item.getId());
    itemDto.setCreatedOn(item.getCreatedOn());
    itemDto.setUpdatedOn(item.getUpdatedOn());
    itemDto.setType(item.getType());
    itemDto.setName(item.getName());
    itemDto.setTotalPrice(item.getTotalPrice());
    itemDto.setUnitPrice(item.getUnitPrice());
    itemDto.setUom(item.getUom());
    itemDto.setVat(item.getVat());
    return itemDto;
  }
}
