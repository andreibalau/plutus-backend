package com.finance.plutus.controller.user;

import com.finance.plutus.model.user.dto.LoggedUserDto;
import com.finance.plutus.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	public LoggedUserDto login(@AuthenticationPrincipal UserDetails userDetails) {
		return userService.login(userDetails);
	}

}
