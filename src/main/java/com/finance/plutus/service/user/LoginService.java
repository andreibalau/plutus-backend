package com.finance.plutus.service.user;

import com.finance.plutus.model.dto.LoggedUserDto;

/** Plutus Created by catalin.matache on 10/1/2020 */
public interface LoginService {
  LoggedUserDto login(String username, String password);
}
