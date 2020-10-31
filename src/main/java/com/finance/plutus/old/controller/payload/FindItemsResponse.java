package com.finance.plutus.old.controller.payload;

import com.finance.plutus.old.model.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindItemsResponse {
  private List<ItemDto> items;
  private int page;
  private int size;
  private long total;
}
