package com.finance.plutus.service;

import com.finance.plutus.model.dto.CreateBusinessDto;
import com.finance.plutus.model.dto.CreateUserDto;

/** Plutus Created by Catalin on 7/1/2020 */
public interface RegisterUserService {
  void register(CreateUserDto createUserDto, CreateBusinessDto createBusinessDto);
}
