package com.finance.plutus.service.impl;

import com.finance.plutus.model.entity.Item;
import com.finance.plutus.repository.ItemRepository;
import com.finance.plutus.service.DeleteItemService;
import com.finance.plutus.service.FindItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class DeleteItemServiceImpl implements DeleteItemService {

  private final ItemRepository itemRepository;
  private final FindItemService findItemService;

  @Override
  public void delete(Long id) {
    Item item = findItemService.findById(id);
    itemRepository.delete(item);
  }
}
