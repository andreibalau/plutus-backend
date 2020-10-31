package com.finance.plutus.old.controller.payload;

import com.finance.plutus.old.model.dto.CreateItemDto;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class CreateItemRequest {
  private CreateItemDto item;
}
