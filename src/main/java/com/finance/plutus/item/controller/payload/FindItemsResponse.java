package com.finance.plutus.item.controller.payload;

import com.finance.plutus.item.model.ItemDto;
import lombok.*;

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
