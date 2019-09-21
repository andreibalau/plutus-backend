package com.finance.plutus.repository.user;

import com.finance.plutus.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface UserRepository extends JpaRepository<User, Long> { }
