package com.finance.plutus.repository;

import java.util.Optional;

import com.finance.plutus.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/** Plutus Created by catalin on 7/1/2020 */
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);
}
