package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.UserException;
import com.finance.plutus.model.user.User;
import com.finance.plutus.model.user.dto.UpdateUserDto;
import com.finance.plutus.repository.user.UserRepository;
import com.finance.plutus.service.user.UpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
@Service
@RequiredArgsConstructor
public class UpdateServiceImpl implements UpdateService {

	private final UserRepository userRepository;

	@Override
	public void update(Long userId, UpdateUserDto updateUserDto) {
		User user = findUser(userId);
		updateUser(user, updateUserDto);
	}

	private User findUser(Long userId) {
		return userRepository
				.findById(userId)
				.orElseThrow(UserException::userNotFound);
	}

	private void updateUser(User user, UpdateUserDto updateUserDto) {
		user.setFirstName(updateUserDto.getFirstName());
		user.setLastName(updateUserDto.getLastName());
		user.setPhone(updateUserDto.getPhone());
		user.setUpdatedOn(System.currentTimeMillis());
		userRepository.save(user);
	}

}
