package com.finance.plutus.service.impl;

import com.finance.plutus.exception.WrongCredentialsException;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/** Plutus Created by catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class FindUserServiceImpl implements FindUserService {

  private final UserRepository userRepository;

  @Override
  public User findByEmailAndPassword(String email, String password) {
    return userRepository
        .findByEmailAndPassword(email, password)
        .orElseThrow(WrongCredentialsException::new);
  }

  @Override
  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
