package com.finance.plutus.item.controller.payload;

import com.finance.plutus.item.model.CreateItemDto;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class CreateItemRequest {
  private CreateItemDto item;
}
