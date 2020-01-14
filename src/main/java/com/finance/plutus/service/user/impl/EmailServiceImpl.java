package com.finance.plutus.service.user.impl;

import com.finance.plutus.repository.user.UserRepository;
import com.finance.plutus.service.user.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final UserRepository userRepository;

	@Override
	public boolean exists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
}
