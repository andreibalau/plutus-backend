package com.finance.plutus.item.controller.payload;

import com.finance.plutus.item.model.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindItemsResponse {
  private int page;
  private int size;
  private long total;
  private List<ItemDto> items;
}
