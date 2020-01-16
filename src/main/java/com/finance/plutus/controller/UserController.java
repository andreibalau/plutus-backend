package com.finance.plutus.controller;

import com.finance.plutus.api.UserApi;
import com.finance.plutus.model.user.dto.AuthenticationDto;
import com.finance.plutus.model.user.dto.EmailExistenceCheckDto;
import com.finance.plutus.model.user.dto.EmailExistenceDto;
import com.finance.plutus.model.user.dto.LoggedUserDto;
import com.finance.plutus.model.user.dto.ProfileUserDto;
import com.finance.plutus.model.user.dto.RegistrationDto;
import com.finance.plutus.model.user.dto.UpdateUserDto;
import com.finance.plutus.service.user.EmailService;
import com.finance.plutus.service.user.LoginService;
import com.finance.plutus.service.user.ProfileService;
import com.finance.plutus.service.user.RegisterService;
import com.finance.plutus.service.user.UpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

	private final LoginService loginService;
	private final RegisterService registerService;
	private final EmailService emailService;
	private final UpdateService updateService;
	private final ProfileService profileService;

	@Override
	public void register(RegistrationDto registrationDto) {
		registerService.register(registrationDto);
	}

	@Override
	public LoggedUserDto login(AuthenticationDto authenticationDto) {
		return loginService.login(authenticationDto);
	}

	@Override
	public void update(Long userId, UpdateUserDto updateUserDto) {
		updateService.update(userId, updateUserDto);
	}

	@Override
	public ProfileUserDto getProfile(Long userId) {
		return profileService.findProfile(userId);
	}

	@Override
	public EmailExistenceDto checkEmail(EmailExistenceCheckDto emailExistenceCheckDto) {
		return new EmailExistenceDto(emailService.exists(emailExistenceCheckDto.getEmail()));
	}

}
