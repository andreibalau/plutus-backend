package com.finance.plutus.old.service.item.impl;

import com.finance.plutus.old.model.entity.Item;
import com.finance.plutus.old.repository.ItemRepository;
import com.finance.plutus.old.service.item.DeleteItemService;
import com.finance.plutus.old.service.item.FindItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class DeleteItemServiceImpl implements DeleteItemService {

  private final ItemRepository itemRepository;
  private final FindItemService findItemService;

  @Override
  @Transactional
  public void delete(UUID id) {
    Item item = findItemService.findEntityById(id);
    itemRepository.delete(item);
  }
}
