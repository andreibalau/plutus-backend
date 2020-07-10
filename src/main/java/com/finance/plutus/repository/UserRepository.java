package com.finance.plutus.repository;

import com.finance.plutus.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** Plutus Created by catalin on 7/1/2020 */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByEmailAndPassword(String email, String password);

  boolean existsByEmail(String email);
}
