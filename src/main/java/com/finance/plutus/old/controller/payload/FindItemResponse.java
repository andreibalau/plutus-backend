package com.finance.plutus.old.controller.payload;

import com.finance.plutus.item.model.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
@AllArgsConstructor
public class FindItemResponse {
  private ItemDto item;
}
