package com.finance.plutus.service.user;

import java.util.Optional;

import com.finance.plutus.model.dto.UserDto;
import com.finance.plutus.model.entity.User;

/** Plutus Created by catalin on 7/1/2020 */
public interface FindUserService {

  Optional<User> findByEmail(String email);

  UserDto findDtoByEmail(String email);
}
