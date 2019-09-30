package com.finance.plutus.service.user;

import com.finance.plutus.model.user.dto.LoggedUserDto;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface Login {
	LoggedUserDto login(UserDetails userDetails);
}
