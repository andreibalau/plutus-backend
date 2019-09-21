package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.ServiceException;
import com.finance.plutus.model.user.User;
import com.finance.plutus.model.user.dto.RegistrationDto;
import com.finance.plutus.repository.user.UserRepository;
import com.finance.plutus.service.user.EmailChecker;
import com.finance.plutus.service.user.Register;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Service
@RequiredArgsConstructor
public class RegisterImpl implements Register {

	private final UserRepository userRepository;
	private final EmailChecker emailChecker;

	@Override
	public void register(RegistrationDto registrationDto) {
		if (emailChecker.exists(registrationDto.getEmail().toLowerCase())) {
			throw ServiceException.emailAlreadyExists();
		}
		User user = registrationDto.toUser();
		user.setCreatedOn(System.currentTimeMillis());
		user.setUpdatedOn(System.currentTimeMillis());
		userRepository.save(user);
	}

}
