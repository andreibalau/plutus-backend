package com.finance.plutus.app.payload;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by Catalin on 1/23/2021 */
@Getter
@Setter
public class PlutusRequest<T> {
  T data;
}
