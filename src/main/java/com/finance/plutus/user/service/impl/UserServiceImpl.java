package com.finance.plutus.user.service.impl;

import com.finance.plutus.user.model.Business;
import com.finance.plutus.user.model.BusinessDto;
import com.finance.plutus.user.model.CreateUserDto;
import com.finance.plutus.user.model.LoggedUserDto;
import com.finance.plutus.user.service.BusinessUpdater;
import com.finance.plutus.user.service.UserAuthenticator;
import com.finance.plutus.user.service.UserCreator;
import com.finance.plutus.user.service.UserFinder;
import com.finance.plutus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserFinder userFinder;
  private final UserCreator userCreator;
  private final BusinessUpdater businessUpdater;
  private final UserAuthenticator userAuthenticator;

  @Override
  public BusinessDto getBusiness() {
    Business business = userFinder.getBusiness();
    return BusinessDto.mapFromEntity(business);
  }

  @Override
  public void register(CreateUserDto user) {
    userCreator.register(user);
  }

  @Override
  public LoggedUserDto login(String username, String password) {
    return userAuthenticator.login(username, password);
  }

  @Override
  public void updateBusiness(UUID userId, BusinessDto business) {
    businessUpdater.update(userId, business);
  }
}
