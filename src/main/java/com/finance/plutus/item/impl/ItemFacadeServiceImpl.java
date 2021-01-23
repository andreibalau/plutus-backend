package com.finance.plutus.item.impl;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.item.ItemFacadeService;
import com.finance.plutus.item.ItemMapper;
import com.finance.plutus.item.ItemService;
import com.finance.plutus.item.dto.CreateItemDto;
import com.finance.plutus.item.dto.ItemDto;
import com.finance.plutus.item.dto.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class ItemFacadeServiceImpl implements ItemFacadeService {

  private final ItemService itemService;
  private final ItemMapper itemMapper;

  @Override
  public EntityCreatedResponse create(PlutusRequest<CreateItemDto> request) {
    UUID id = itemService.create(request.getData());
    return new EntityCreatedResponse(id);
  }

  @Override
  public void update(UUID id, PlutusRequest<UpdateItemDto> request) {
    itemService.update(id, request.getData());
  }

  @Override
  public void delete(UUID id) {
    itemService.delete(id);
  }

  @Override
  public PlutusResponse<List<ItemDto>> findAll(int page, int size) {
    List<ItemDto> items =
        itemService.findAll(page, size).stream()
            .map(itemMapper::mapToDto)
            .collect(Collectors.toList());
    return new PlutusResponse<>(items);
  }
}
