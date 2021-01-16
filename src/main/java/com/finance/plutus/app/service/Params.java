package com.finance.plutus.app.service;

import java.util.Map;

/** Plutus Created by Catalin on 1/16/2021 */
public interface Params<T> {
  String getName();

  Map<String, Object> getMap();

  void submit(T t);
}
