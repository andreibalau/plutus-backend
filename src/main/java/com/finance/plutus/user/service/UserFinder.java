package com.finance.plutus.user.service;

import com.finance.plutus.user.model.Business;
import com.finance.plutus.user.model.User;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface UserFinder {
  User findByEmail(String email);

  User findById(UUID id);

  Business getBusiness();

  boolean existsByEmail(String email);

  void validateEmailExistence(String email);
}
