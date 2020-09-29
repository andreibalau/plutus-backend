package com.finance.plutus.security;

import com.finance.plutus.model.entity.UserRole;
import com.finance.plutus.service.user.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/** Plutus Created by Catalin on 9/29/2020 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final FindUserService findUserService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return findUserService
        .findByEmail(username)
        .map(user -> createUserDetails(user.getEmail(), user.getPassword(), user.getRole()))
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  private User createUserDetails(String username, String password, UserRole role) {
    GrantedAuthority authority = new SimpleGrantedAuthority(role.name());
    return new User(username, password, Collections.singletonList(authority));
  }
}
