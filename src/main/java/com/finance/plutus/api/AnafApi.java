package com.finance.plutus.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import com.finance.plutus.model.anaf.InfoRequestDto;
import com.finance.plutus.model.anaf.InfoResponseDto;
import com.finance.plutus.util.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@RequestMapping(Api.ANAF)
public interface AnafApi {

	/**
	 * Find informations
	 */
	@ApiOperation(value = "Find informations for a fiscal code",
			nickname = "find",
			tags = "anaf-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized"),
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
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	InfoResponseDto find(
			@ApiParam(value = "The request containing the fiscal code and the date", required = true)
			@Valid @RequestBody InfoRequestDto infoRequest);

}
