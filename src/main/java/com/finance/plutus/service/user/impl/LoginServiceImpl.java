package com.finance.plutus.service.user.impl;

import com.finance.plutus.model.dto.LoggedUserDto;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.security.JwtTokenUtil;
import com.finance.plutus.service.user.FindUserService;
import com.finance.plutus.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final JwtTokenUtil jwtTokenUtil;
  private final FindUserService findUserService;

  @Override
  public LoggedUserDto login(String username, String password) {
    User user = findUserService.findByEmailAndPassword(username, password);
    String token = jwtTokenUtil.generate(user.getEmail());
    return LoggedUserDto.builder().id(user.getId()).email(user.getEmail()).token(token).build();
  }
}
