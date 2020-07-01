package com.finance.plutus.service.impl;

import java.util.Optional;

import com.finance.plutus.model.entity.User;
import com.finance.plutus.service.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class FindUserServiceImpl implements FindUserService {
  @Override
  public Optional<User> findUserByEmail(String email) {
    return Optional.empty();
  }
}
