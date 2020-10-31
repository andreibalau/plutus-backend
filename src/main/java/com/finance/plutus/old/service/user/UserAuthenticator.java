package com.finance.plutus.old.service.user;

import com.finance.plutus.old.model.dto.LoggedUserDto;

/** Plutus Created by catalin.matache on 10/1/2020 */
public interface UserAuthenticator {
  LoggedUserDto login(String username, String password);
}
