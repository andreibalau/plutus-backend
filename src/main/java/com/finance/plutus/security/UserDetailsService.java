package com.finance.plutus.security;

import java.util.Collections;

import com.finance.plutus.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmailForAuthentication(username)
                .map(user -> new User(user.getEmail(), user.getPassword(), Collections.emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

