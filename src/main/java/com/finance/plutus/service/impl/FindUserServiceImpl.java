package com.finance.plutus.service.impl;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.exception.WrongCredentialsException;
import com.finance.plutus.model.dto.UserProfileDto;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public UserProfileDto getProfile() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = findByEmail(email).orElseThrow(() -> new EntityNotFoundException("user"));
    return map(user);
  }

  private UserProfileDto map(User user) {
    UserProfileDto userProfileDto = new UserProfileDto();
    userProfileDto.setCreatedOn(user.getCreatedOn());
    userProfileDto.setUpdatedOn(user.getUpdatedOn());
    userProfileDto.setEmail(user.getEmail());
    userProfileDto.setFirstName(user.getFirstName());
    userProfileDto.setLastName(user.getLastName());
    userProfileDto.setPhone(user.getPhone());
    return userProfileDto;
  }
}
