package com.finance.plutus.user.service;

import com.finance.plutus.user.model.LoggedUserDto;

/** Plutus Created by catalin.matache on 10/1/2020 */
public interface UserAuthenticator {
  LoggedUserDto login(String username, String password);
}
