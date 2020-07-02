package com.finance.plutus.service;

import java.util.Optional;

import com.finance.plutus.model.dto.UserProfileDto;
import com.finance.plutus.model.entity.User;
import org.springframework.lang.NonNull;

/** Plutus Created by catalin on 7/1/2020 */
public interface FindUserService {

  User findByEmailAndPassword(@NonNull String email, @NonNull String password);

  Optional<User> findByEmail(@NonNull String email);

  UserProfileDto getProfile();
}
