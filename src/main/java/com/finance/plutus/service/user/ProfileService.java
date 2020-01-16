package com.finance.plutus.service.user;

import com.finance.plutus.model.user.dto.ProfileUserDto;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
public interface ProfileService {
	ProfileUserDto findProfile(Long userId);
}
