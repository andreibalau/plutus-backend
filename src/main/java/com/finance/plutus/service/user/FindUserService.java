package com.finance.plutus.service.user;

import com.finance.plutus.model.dto.UserProfileDto;
import com.finance.plutus.model.entity.User;
import org.springframework.lang.NonNull;

import java.util.Optional;

/** Plutus Created by catalin on 7/1/2020 */
public interface FindUserService {

  User findByEmailAndPassword(@NonNull String email, @NonNull String password);

  Optional<User> findByEmail(@NonNull String email);

  UserProfileDto getProfile();
}
