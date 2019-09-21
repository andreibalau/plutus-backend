package com.finance.plutus.service.user;

import com.finance.plutus.model.user.dto.LoggedUserDto;
import com.finance.plutus.model.user.dto.RegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface UserService {
	LoggedUserDto login(UserDetails userDetails);
	void register(RegistrationDto registrationDto);
	boolean existsEmail(String email);
}
