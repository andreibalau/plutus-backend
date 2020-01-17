package com.finance.plutus.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;
import java.util.List;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.partner.dto.ModifyPartnerDto;
import com.finance.plutus.model.partner.dto.PartnerDto;
import com.finance.plutus.util.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(Api.PARTNERS)
public interface PartnerApi {

	/**
	 * Partner creation
	 */
	@ApiOperation(value = "Create a new partner",
			nickname = "create",
			tags = "partner-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Partner creation successfully"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 400, message = "Bad request"),
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
	@ResponseStatus(CREATED)
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	EntityCreatedDto create(
			@ApiParam(value = "The request containing the partner payload", required = true)
			@Valid @RequestBody ModifyPartnerDto modifyPartnerDto);

	/**
	 * Find all partners
	 */
	@ApiOperation(value = "Find all partners",
			nickname = "findAll",
			tags = "partner-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 400, message = "Bad request"),
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
	@GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	List<PartnerDto> findAll();

	/**
	 * Find partner by id
	 */
	@ApiOperation(value = "Find partner by id",
			nickname = "findById",
			tags = "partner-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 404, message = "Partner not found"),
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
	@GetMapping(value = "/{partnerId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	PartnerDto findById(
			@ApiParam(value = "The id of the partner", required = true)
			@PathVariable Long partnerId);

	/**
	 * Update a partner
	 */
	@ApiOperation(value = "Update partner",
			nickname = "update",
			tags = "partner-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 404, message = "Partner not found"),
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
	@PutMapping(value = "/{partnerId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	void update(
			@ApiParam(value = "The id of the partner", required = true)
			@PathVariable Long partnerId,
			@ApiParam(value = "The partner payload", required = true)
			@Valid @RequestBody ModifyPartnerDto partnerDto);

	/**
	 * Delete a partner
	 */
	@ApiOperation(value = "Delete a partner",
			nickname = "delete",
			tags = "partner-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 404, message = "Partner not found"),
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
	@DeleteMapping(value = "/{partnerId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	void delete(
			@ApiParam(value = "The id of the partner", required = true)
			@PathVariable Long partnerId);

}
