package com.finance.plutus.old.service.user;

import com.finance.plutus.old.model.entity.User;

import java.util.UUID;

/** Plutus Created by catalin on 7/1/2020 */
public interface FindUserService {

  User findByEmail(String email);

  User findById(UUID id);
}
