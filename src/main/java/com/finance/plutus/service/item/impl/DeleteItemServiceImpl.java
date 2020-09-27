package com.finance.plutus.service.item.impl;

import com.finance.plutus.model.entity.Item;
import com.finance.plutus.repository.ItemRepository;
import com.finance.plutus.service.item.DeleteItemService;
import com.finance.plutus.service.item.FindItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class DeleteItemServiceImpl implements DeleteItemService {

  private final ItemRepository itemRepository;
  private final FindItemService findItemService;

  @Override
  @Transactional
  public void delete(String id) {
    Item item = findItemService.findEntityById(id);
    itemRepository.delete(item);
  }
}
