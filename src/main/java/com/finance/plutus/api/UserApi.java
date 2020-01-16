package com.finance.plutus.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import com.finance.plutus.model.user.dto.AuthenticationDto;
import com.finance.plutus.model.user.dto.EmailExistenceCheckDto;
import com.finance.plutus.model.user.dto.EmailExistenceDto;
import com.finance.plutus.model.user.dto.LoggedUserDto;
import com.finance.plutus.model.user.dto.ProfileUserDto;
import com.finance.plutus.model.user.dto.RegistrationDto;
import com.finance.plutus.model.user.dto.UpdateUserDto;
import com.finance.plutus.util.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Plutus
 * Created by catalin on 1/15/2020
 */
@RequestMapping(Api.USERS)
public interface UserApi {

	/**
	 * User registration
	 */
	@ApiOperation(value = "Register a new user",
			nickname = "register",
			tags = "user-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "User registration successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 405, message = "Method not allowed"),
			@ApiResponse(code = 415, message = "Unsupported media type"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type",
					value = "The content type of the request body",
					paramType = "header", required = true, defaultValue = "application/json")
	})
	@ResponseStatus(CREATED)
	@PostMapping(value = Api.REGISTER, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	void register(
			@ApiParam(value = "The request containing the user data", required = true)
			@RequestBody @Valid RegistrationDto registrationDto);

	/**
	 * Email existing check
	 */
	@ApiOperation(value = "Check an email for existence",
			nickname = "checkEmail",
			tags = "user-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User registration successfully"),
			@ApiResponse(code = 405, message = "Method not allowed"),
			@ApiResponse(code = 415, message = "Unsupported media type"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type",
					value = "The content type of the request body",
					paramType = "header", required = true, defaultValue = "application/json"),
	})
	@PostMapping(value = Api.CHECK_EMAIL, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	EmailExistenceDto checkEmail(
			@ApiParam(value = "The request containing email to check for existence", required = true)
			@RequestBody EmailExistenceCheckDto emailExistenceCheckDto);

	/**
	 * User login
	 */
	@ApiOperation(value = "Authenticate an user",
			nickname = "login",
			tags = "user-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Authentication successfully"),
			@ApiResponse(code = 400, message = "Wrong credentials"),
			@ApiResponse(code = 405, message = "Method not allowed"),
			@ApiResponse(code = 415, message = "Unsupported media type"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type",
					value = "The content type of the request body",
					paramType = "header", required = true, defaultValue = "application/json"),
	})
	@PostMapping(value = Api.LOGIN, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	LoggedUserDto login(
			@ApiParam(value = "The request containing the user details for login", required = true)
			@RequestBody AuthenticationDto authenticationDto);

	/**
	 * User update
	 */
	@ApiOperation(value = "Update an user",
			nickname = "update",
			tags = "user-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Updated"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 405, message = "Method not allowed"),
			@ApiResponse(code = 415, message = "Unsupported media type"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type",
					value = "The content type of the request body",
					paramType = "header", required = true, defaultValue = "application/json"),
			@ApiImplicitParam(name = "Authorization",
					value = "The authorization token header",
					paramType = "header", required = true),
	})
	@PutMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	void update(
			@ApiParam(value = "The id of the user", required = true)
			@PathVariable Long userId,
			@ApiParam(value = "The request containing the user payload", required = true)
			@Valid @RequestBody UpdateUserDto updateUserDto);

	/**
	 * User profile
	 */
	@ApiOperation(value = "Get an user profile",
			nickname = "getProfile",
			tags = "user-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 405, message = "Method not allowed"),
			@ApiResponse(code = 415, message = "Unsupported media type"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type",
					value = "The content type of the request body",
					paramType = "header", required = true, defaultValue = "application/json"),
			@ApiImplicitParam(name = "Authorization",
					value = "The authorization token header",
					paramType = "header", required = true),
	})
	@GetMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ProfileUserDto getProfile(
			@ApiParam(value = "The id of the user", required = true)
			@PathVariable Long userId);

}
