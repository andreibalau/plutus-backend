package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.UserException;
import com.finance.plutus.model.exchange.Currency;
import com.finance.plutus.model.user.Settings;
import com.finance.plutus.model.user.User;
import com.finance.plutus.model.user.dto.RegistrationDto;
import com.finance.plutus.repository.user.UserRepository;
import com.finance.plutus.service.user.EmailService;
import com.finance.plutus.service.user.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

	private final UserRepository userRepository;
	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void register(RegistrationDto registrationDto) {
		if (emailService.exists(registrationDto.getEmail().toLowerCase())) {
			throw UserException.emailAlreadyExists();
		}
		User user = registrationDto.toUser();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreatedOn(System.currentTimeMillis());
		user.setUpdatedOn(System.currentTimeMillis());
		user.setSettings(makeDefaultSettings());
		userRepository.save(user);
	}

	private Settings makeDefaultSettings() {
		return Settings
				.builder()
				.createdOn(System.currentTimeMillis())
				.updatedOn(System.currentTimeMillis())
				.currency(Currency.RON)
				.build();
	}

}
