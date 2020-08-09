package com.finance.plutus.controller.payload;

import com.finance.plutus.model.dto.UpdateItemDto;
import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 8/7/2020 */
@Getter
@Setter
public class UpdateItemRequest {
  private UpdateItemDto item;
}
