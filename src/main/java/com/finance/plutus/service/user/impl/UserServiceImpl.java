package com.finance.plutus.service.user.impl;

import com.finance.plutus.model.user.dto.LoggedUserDto;
import com.finance.plutus.model.user.dto.RegistrationDto;
import com.finance.plutus.service.user.EmailChecker;
import com.finance.plutus.service.user.Login;
import com.finance.plutus.service.user.Register;
import com.finance.plutus.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final Login login;
	private final Register register;
	private final EmailChecker emailChecker;

	@Override
	public LoggedUserDto login(UserDetails userDetails) {
		return login.login(userDetails);
	}

	@Override
	public void register(RegistrationDto registrationDto) {
		register.register(registrationDto);
	}

	@Override
	public boolean existsEmail(String email) {
		return emailChecker.exists(email);
	}
}
