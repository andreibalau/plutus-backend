package com.finance.plutus.user.service;

import com.finance.plutus.user.model.BusinessDto;
import com.finance.plutus.user.model.CreateUserDto;
import com.finance.plutus.user.model.LoggedUserDto;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface UserService {

  BusinessDto getBusiness();

  void register(CreateUserDto user);

  LoggedUserDto login(String username, String password);

  void updateBusiness(UUID userId, BusinessDto business);
}
