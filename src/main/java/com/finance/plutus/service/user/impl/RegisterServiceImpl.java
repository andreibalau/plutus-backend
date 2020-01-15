package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.UserException;
import com.finance.plutus.model.user.User;
import com.finance.plutus.model.user.dto.RegistrationDto;
import com.finance.plutus.repository.user.UserRepository;
import com.finance.plutus.service.user.EmailService;
import com.finance.plutus.service.user.RegisterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
	private final ModelMapper modelMapper;

	@Override
	public void register(RegistrationDto registrationDto) {
		if (emailService.exists(registrationDto.getEmail().toLowerCase())) {
			throw UserException.emailAlreadyExists();
		}
		User user = modelMapper.map(registrationDto, User.class);
		user.setEmail(user.getEmail().toLowerCase());
		user.setCreatedOn(System.currentTimeMillis());
		user.setUpdatedOn(System.currentTimeMillis());
		userRepository.save(user);
	}

}
