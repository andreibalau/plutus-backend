package com.finance.plutus.user.service.impl;

import com.finance.plutus.user.model.Business;
import com.finance.plutus.user.model.BusinessDto;
import com.finance.plutus.user.service.UserFinder;
import com.finance.plutus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserFinder userFinder;

  @Override
  public BusinessDto getBusiness(Jwt jwt) {
    String username = jwt.getClaim("username");
    Business business = userFinder.getBusiness(username);
    return BusinessDto.mapFromEntity(business);
  }
}
