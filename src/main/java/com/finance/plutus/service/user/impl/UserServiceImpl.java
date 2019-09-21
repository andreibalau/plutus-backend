package com.finance.plutus.service.user.impl;

import com.finance.plutus.model.user.dto.LoggedUserDto;
import com.finance.plutus.service.user.Login;
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

	@Override
	public LoggedUserDto login(UserDetails userDetails) {
		return login.login(userDetails);
	}
}
