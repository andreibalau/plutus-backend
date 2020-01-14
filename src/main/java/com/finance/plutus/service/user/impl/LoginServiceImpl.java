package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.UserException;
import com.finance.plutus.model.user.dto.LoggedUserDto;
import com.finance.plutus.repository.user.UserRepository;
import com.finance.plutus.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;

	@Override
	public LoggedUserDto login(UserDetails userDetails) {
		return userRepository
				.findByEmail(userDetails.getUsername())
				.map(LoggedUserDto::from)
				.orElseThrow(UserException::userNotFound);
	}
}
