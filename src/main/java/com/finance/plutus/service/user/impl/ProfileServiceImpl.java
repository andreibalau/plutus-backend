package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.UserException;
import com.finance.plutus.model.user.dto.ProfileUserDto;
import com.finance.plutus.repository.user.UserRepository;
import com.finance.plutus.service.user.ProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Override
	public ProfileUserDto findProfile(Long userId) {
		return userRepository
				.findById(userId)
				.map(user -> modelMapper.map(user, ProfileUserDto.class))
				.orElseThrow(UserException::userNotFound);
	}

}
