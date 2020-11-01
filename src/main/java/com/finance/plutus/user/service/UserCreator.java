package com.finance.plutus.user.service;

import com.finance.plutus.user.model.CreateUserDto;

/** Plutus Created by Catalin on 11/1/2020 */
public interface UserCreator {
  void register(CreateUserDto user);
}
