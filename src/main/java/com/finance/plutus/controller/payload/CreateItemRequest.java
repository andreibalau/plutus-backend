package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.CreateItemDto;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class CreateItemRequest {
  private CreateItemDto item;
}
