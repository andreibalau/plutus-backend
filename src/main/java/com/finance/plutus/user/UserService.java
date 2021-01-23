package com.finance.plutus.user;

import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
public interface UserService {
  User findByEmail(String email);

  User findById(UUID id);

  Business getBusiness(String username);
}
