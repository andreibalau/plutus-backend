package com.finance.plutus.service.impl;

import com.finance.plutus.model.dto.LoggedUserDto;
import com.finance.plutus.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
  @Override
  public LoggedUserDto login(String username, String password) {
    return null;
  }
}
