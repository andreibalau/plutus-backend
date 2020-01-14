package com.finance.plutus.controller.user;

import com.finance.plutus.model.user.dto.EmailExistenceCheckDto;
import com.finance.plutus.model.user.dto.EmailExistenceDto;
import com.finance.plutus.model.user.dto.LoggedUserDto;
import com.finance.plutus.model.user.dto.RegistrationDto;
import com.finance.plutus.service.user.EmailService;
import com.finance.plutus.service.user.LoginService;
import com.finance.plutus.service.user.RegisterService;
import com.finance.plutus.util.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(Api.USERS)
public class UserController {

	private final LoginService loginService;
	private final RegisterService registerService;
	private final EmailService emailService;

	@PostMapping(Api.LOGIN)
	public LoggedUserDto login(@AuthenticationPrincipal UserDetails userDetails) {
		return loginService.login(userDetails);
	}

	@ResponseStatus(CREATED)
	@PostMapping(Api.REGISTER)
	public void register(@Valid @RequestBody RegistrationDto registrationDto) {
		registerService.register(registrationDto);
	}

	@PostMapping(Api.CHECK_EMAIL)
	public EmailExistenceDto existsEmail(@RequestBody EmailExistenceCheckDto emailExistenceCheckDto) {
		return new EmailExistenceDto(emailService.exists(emailExistenceCheckDto.getEmail()));
	}

}
