package com.finance.plutus.repository.user;

import com.finance.plutus.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
	Optional<User> findByEmailForAuthentication(@Param("email") String email);
}
