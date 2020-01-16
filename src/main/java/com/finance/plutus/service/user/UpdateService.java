package com.finance.plutus.service.user;

import com.finance.plutus.model.user.dto.UpdateUserDto;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
public interface UpdateService {
	void update(Long userId, UpdateUserDto updateUserDto);
}
