package com.finance.plutus.user.repository;

import com.finance.plutus.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/** Plutus Created by catalin on 7/1/2020 */
public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);
}
